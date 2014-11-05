/**
 * 
 */
package com.hehua.commons.security.web.whitelist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Lucas
 * 
 */
public class ProfileCSSBlackList implements BlackList, CSSSupport {

	Map<String, Set<String>> bl = new HashMap<String, Set<String>>();

	protected ProfileCSSBlackList() {
		Set<String> set = new HashSet<String>();
		set.add("expression");
		set.add("script");
		bl.put(null, set);
	}

	@Override
	public Map<String, Set<String>> getTags() {
		return bl;
	}

}
