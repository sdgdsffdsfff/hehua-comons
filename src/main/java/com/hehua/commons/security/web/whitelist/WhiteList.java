/**
 * 
 */
package com.hehua.commons.security.web.whitelist;

import java.util.Set;

import com.google.common.collect.Multimap;
import com.hehua.commons.tuple.FourTuple;

/**
 * @author Lucas
 * 
 */
public interface WhiteList {

    public Multimap<String, FourTuple<String, String, Boolean, Set<String>>> getTags();

    public static WhiteList Publisher = new PublisherWhiteList();

    public static WhiteList Blank = new BlankWhiteList();

}
