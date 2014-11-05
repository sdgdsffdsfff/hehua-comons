/**
 * 
 */
package com.hehua.commons.security.web.whitelist;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.common.collect.Multimap;
import com.hehua.commons.tuple.FourTuple;

/**
 * @author Lucas
 * 
 */
public class HTMLCleaner extends CleanerImpl implements Cleaner {

    protected HTMLCleaner() {
    }

    @Override
    public String clean(String unsafe, WhiteList wl) {
        if (wl instanceof HTMLSupport) {
            Multimap<String, FourTuple<String, String, Boolean, Set<String>>> tags = wl.getTags();
            DianWhitelist w = new DianWhitelist();
            w.addTags(tags.keySet().toArray(new String[] {}));
            for (Entry<String, Collection<FourTuple<String, String, Boolean, Set<String>>>> e : tags
                    .asMap().entrySet()) {
                String tagName = e.getKey();
                Collection<FourTuple<String, String, Boolean, Set<String>>> tuples = e.getValue();
                if (CollectionUtils.isEmpty(tuples)) {
                    w.addAttributes(tagName, new String[] {});
                } else {
                    for (FourTuple<String, String, Boolean, Set<String>> tuple : tuples) {
                        if (tuple == null) {
                            continue;
                        }
                        String attribute = tuple.first;
                        String protocol = tuple.second;
                        Set<String> enforcedAttributes = tuple.fourth;
                        w.addAttributes(tagName, attribute);
                        if (protocol != null) {
                            w.addProtocols(tagName, attribute, protocol);
                        }
                        if (!CollectionUtils.isEmpty(enforcedAttributes)) {
                            for (String ea : enforcedAttributes) {
                                w.addAttributesValues(tagName, attribute, ea);
                            }
                        }
                        if (tuple.third) {
                            w.addRequiredAttributes(tagName, tuple.first);
                        }
                    }
                }
            }
            return decodeEmoji(clean(unsafe, w));
        }
        return decodeEmoji(super.clean(unsafe, wl));
    }

    public static String clean(String bodyHtml, DianWhitelist whitelist) {
        Document dirty = Jsoup.parseBodyFragment(bodyHtml, "");
        DianCleaner cleaner = new DianCleaner(whitelist);
        Document clean = cleaner.clean(dirty);
        return decodeEmoji(clean.body().html());
    }

    static Pattern pattern = Pattern.compile("&#(5535[6|7]);&#(5[6|7]\\d+);");

    /**
     * 这个方法会把emoji转义的实体反转回来
     * 
     * @param encodeString
     * @return
     */
    public static String decodeEmoji(String encodeString) {
        Matcher matcher = pattern.matcher(encodeString);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, (char) Integer.parseInt(matcher.group(1)) + ""
                    + (char) Integer.parseInt(matcher.group(2)));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        String source = "<a href=\"http://www.baidu.com\" class = \"edui-filter-underline   color: rgb(0, 0, 153); display: block; \" style=\"text-decoration: line-through; color: red1; color: rgb(227, 108, 9); background-color: rgb(235, 241, 221);align:left;\"></a>\n"
                + "<img id=\"test\" src=\"\" />\r\n"
                + "<img id=\"test\" title=\"abc\"/>\r\n"
                + "<img title=\"abc\"/>\r\n"
                + "<a title=\"test\">\r\n"
                + "<img id=\"test\" fid=\"xxx\" src=\"http://img.hehuababy.com/test.jpg\"/>\r\n"
                + "<img id=\"test\" title=\"abc\"/>\r\n"
                + "<img title=\"abc\"/>\r\n"
                + "</a>"
                + "<p class=\"test\">ahha</p>\r\n"
                + "<p>\r\n"
                + "<a href=\"abc\">\r\n"
                + "test\r\n" + "</a>\r\n" + "</p>";
        System.err.println(source);
        System.err.println("============");
        System.err.println(Cleaner.HTML.clean(source, WhiteList.Publisher));
    }
}
