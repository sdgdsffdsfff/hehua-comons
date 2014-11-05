/**
 * 
 */
package com.hehua.commons.security.web.whitelist;

import java.util.List;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;

/**
 * 
 * 临时过滤
 * 
 * @author zhihua
 *
 */
public class ImageCleaner {

    private List<Pattern> acceptPatterns = Lists
            .newArrayList(Pattern
                    .compile("^http://img\\.hehuababy\\.com\\/[0-9a-zA-Z\\-]+\\.(png|jpg|jpeg|jepg|bmp|gif)$"));

    public String cleanImageUrl(String fid, String url) {
        for (Pattern pattern : acceptPatterns) {
            if (pattern.matcher(url).matches()) {
                return url;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ImageCleaner imageCleaner = new ImageCleaner();
        System.out.println(imageCleaner.cleanImageUrl("", "http://img.hehuababy.com/abc.gif"));
    }
}
