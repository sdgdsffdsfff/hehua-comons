/**
 * 
 */
package com.hehua.commons.security.web.whitelist;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleRule;
import org.w3c.dom.css.CSSStyleSheet;

import com.steadystate.css.dom.CSSStyleDeclarationImpl;
import com.steadystate.css.dom.Property;
import com.steadystate.css.parser.CSSOMParser;

/**
 * 对于每个Property来说，Name是做完整匹配，Value是用contain 比方说
 * background-color:rgb(229,206,0) 中color就不会被匹配上Name 可是29会匹配上Value
 * 
 * @author Lucas
 * 
 */
public class CSSCleaner extends CleanerImpl implements Cleaner {

    //MAX 10mb
    int MAX_FILE_SIZE = 10 * 1024 * 1024;

    Logger logger = Logger.getLogger(CSSCleaner.class);

    protected CSSCleaner() {
    }

    @Override
    public String clean(String unsafe, BlackList bl) {
        if (unsafe.length() > MAX_FILE_SIZE) {
            unsafe = unsafe.substring(0, MAX_FILE_SIZE);
        }
        if (bl instanceof CSSSupport) {
            CSSOMParser parser = new CSSOMParser();
            CSSStyleSheet css = null;
            Reader r = new InputStreamReader(new ByteArrayInputStream(unsafe.getBytes()));
            try {
                css = parser.parseStyleSheet(new InputSource(r), null, null);
            } catch (IOException e) {
                throw new CSSException(e);
            }
            CSSRuleList cssRules = css.getCssRules();
            Map<String, Set<String>> tags = bl.getTags();
            StringBuilder cssRet = new StringBuilder();

            Set<String> globalBlack = tags.get(null);

            for (int i = 0; i < cssRules.getLength(); i++) {
                CSSRule rule = cssRules.item(i);
                // not support css3
                if (rule instanceof CSSStyleRule) {
                    CSSStyleDeclarationImpl style = (CSSStyleDeclarationImpl) ((CSSStyleRule) rule)
                            .getStyle();
                    String selectorText = ((CSSStyleRule) rule).getSelectorText();
                    logger.debug("append selector:" + selectorText);
                    cssRet.append(selectorText);
                    logger.debug("append properties begin with {");
                    cssRet.append("{");
                    for (Property p : style.getProperties()) {

                        boolean found = false;
                        if (globalBlack != null) {
                            // found value in global list
                            for (String gb : globalBlack) {
                                if (p.getValue().getCssText().contains(gb)) {
                                    found = true;
                                }
                            }
                        }
                        if (!found && tags.containsKey(p.getName())) {
                            // found tag in blacklist
                            Set<String> blackPropertyValues = tags.get(p.getName());
                            if (blackPropertyValues == null) {
                                // filter the entire property;
                                found = true;
                            } else {
                                for (String blackProperty : blackPropertyValues) {
                                    if (!blackProperty.contains(p.getValue().getCssText())) {
                                        found = true;
                                    }
                                }
                            }
                        }
                        if (!found) {
                            logger.debug("append properties name:" + p.getName());
                            cssRet.append(p.getName());
                            cssRet.append(":");
                            logger.debug("append properties value:" + p.getValue().getCssText());
                            cssRet.append(p.getValue().getCssText());
                            cssRet.append(";");
                        }
                    }
                    cssRet.append("}\r");
                    logger.debug("and temporary is:" + cssRet.toString());
                }
            }
            logger.debug("finally we got css:" + cssRet.toString());
            return cssRet.toString();
        }
        return super.clean(unsafe, bl);
    }
}
