/**
 * 
 */
package com.hehua.commons.security.web.whitelist;

/**
 * @author Lucas
 * 
 */
public interface Cleaner {
	public String clean(String unsafe, BlackList bl);

	public String clean(String unsafe, WhiteList wl);

	public static Cleaner HTML = new HTMLCleaner();
	public static Cleaner CSS = new CSSCleaner();
}
