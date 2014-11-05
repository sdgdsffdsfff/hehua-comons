package com.hehua.commons.security.web.whitelist;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Tag;
import org.jsoup.safety.Whitelist;

/**
 * 从 org.jsoup.safety.Cleaner 拷贝 修改了 Whitelist 类里面的enforcedAttributes用法
 */
public class DianCleaner extends Whitelist {

    private final Log logger = LogFactory.getLog(getClass());

    private DianWhitelist whitelist;

    /**
     * Create a new cleaner, that sanitizes documents using the supplied
     * whitelist.
     * 
     * @param whitelist white-list to clean with
     */
    public DianCleaner(DianWhitelist whitelist) {
        Validate.notNull(whitelist);
        this.whitelist = whitelist;

    }

    /**
     * Creates a new, clean document, from the original dirty document,
     * containing only elements allowed by the whitelist. The original
     * document is not modified. Only elements from the dirt document's
     * <code>body</code> are used.
     * 
     * @param dirtyDocument Untrusted base document to clean.
     * @return cleaned document.
     */
    public Document clean(Document dirtyDocument) {
        Validate.notNull(dirtyDocument);

        Document clean = Document.createShell(dirtyDocument.baseUri());
        copySafeNodes(dirtyDocument.body(), clean.body());

        return clean;
    }

    /**
     * Dertmines if the input document is valid, against the whitelist. It
     * is considered valid if all the tags and attributes in the input HTML
     * are allowed by the whitelist.
     * <p/>
     * This method can be used as a validator for user input forms. An
     * invalid document will still be cleaned successfully using the
     * {@link #clean(Document)} document. If using as a validator, it is
     * recommended to still clean the document to ensure enforced
     * attributes are set correctly, and that the output is tidied.
     * 
     * @param dirtyDocument document to test
     * @return true if no tags or attributes need to be removed; false if
     *         they do
     */
    public boolean isValid(Document dirtyDocument) {
        Validate.notNull(dirtyDocument);

        Document clean = Document.createShell(dirtyDocument.baseUri());
        int numDiscarded = copySafeNodes(dirtyDocument.body(), clean.body());
        return numDiscarded == 0;
    }

    /**
     * Iterates the input and copies trusted nodes (tags, attributes, text)
     * into the destination.
     * 
     * @param source source of HTML
     * @param dest destination element to copy into
     * @return number of discarded elements (that were considered unsafe)
     */
    private int copySafeNodes(Element source, Element dest) {
        List<Node> sourceChildren = source.childNodes();
        int numDiscarded = 0;

        for (Node sourceChild : sourceChildren) {
            if (sourceChild instanceof Element) {
                Element sourceEl = (Element) sourceChild;

                if (whitelist.isSafeTag(sourceEl.tagName())) { // safe, clone and copy safe attrs
                    ElementMeta meta = createSafeElement(sourceEl);

                    // 检查必须的属性是否存在
                    Element destChild = meta.el;
                    if (whitelist.isTagHaveRequiredAttribute(destChild)) {
                        logger.debug("pass req attr check:" + destChild);
                        dest.appendChild(destChild);
                        numDiscarded += meta.numAttribsDiscarded;
                        numDiscarded += copySafeNodes(sourceEl, destChild); // recurs
                    } else {
                        logger.debug("fail to pass req attr check:" + destChild);
                        numDiscarded++;
                        numDiscarded += copySafeNodes(sourceEl, dest);
                    }
                } else { // not a safe tag, but it may have children (els or text) that are, so recurse
                    numDiscarded++;
                    numDiscarded += copySafeNodes(sourceEl, dest);
                }
            } else if (sourceChild instanceof TextNode) {
                TextNode sourceText = (TextNode) sourceChild;
                TextNode destText = new TextNode(sourceText.getWholeText(), sourceChild.baseUri());
                dest.appendChild(destText);
            } // else, we don't care about comments, xml proc instructions, etc
        }
        return numDiscarded;
    }

    private ElementMeta createSafeElement(Element sourceEl) {
        String sourceTag = sourceEl.tagName();
        Attributes destAttrs = new Attributes();
        Element dest = new Element(Tag.valueOf(sourceTag), sourceEl.baseUri(), destAttrs);
        int numDiscarded = 0;

        Attributes sourceAttrs = sourceEl.attributes();
        for (Attribute sourceAttr : sourceAttrs) {
            if (whitelist.isSafeAttribute(sourceTag, sourceEl, sourceAttr)) {
                destAttrs.put(sourceAttr);
            } else {
                numDiscarded++;
            }
        }
        Attributes enforcedAttrs = whitelist.getEnforcedAttributes(sourceTag);
        destAttrs.addAll(enforcedAttrs);

        return new ElementMeta(dest, numDiscarded);
    }

    private static class ElementMeta {

        Element el;

        int numAttribsDiscarded;

        ElementMeta(Element el, int numAttribsDiscarded) {
            this.el = el;
            this.numAttribsDiscarded = numAttribsDiscarded;
        }
    }

}
