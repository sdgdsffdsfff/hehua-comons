/**
 * 
 */
package com.hehua.commons.security.web.whitelist;

/**
 * @author Lucas
 * 
 */
public abstract class CleanerImpl implements Cleaner {
	@Override
	public String clean(String unsafe, BlackList bl) {
		return unsafe;
	}

	@Override
	public String clean(String unsafe, WhiteList wl) {
		return unsafe;
	}

}
