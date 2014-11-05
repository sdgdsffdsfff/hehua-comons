/**
 * 
 */
package com.hehua.commons.security.web.whitelist;

import java.util.Map;
import java.util.Set;

/**
 * @author Lucas
 * 
 */
public interface BlackList {

    Map<String, Set<String>> getTags();

    public static BlackList ProfileTheme = new ProfileCSSBlackList();
}
