package com.hehua.commons.security.web.whitelist;

import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.hehua.commons.tuple.FourTuple;

public class BlankWhiteList implements WhiteList, HTMLSupport {

    private final Multimap<String, FourTuple<String, String, Boolean, Set<String>>> TAGS = HashMultimap
            .create();

    @Override
    public Multimap<String, FourTuple<String, String, Boolean, Set<String>>> getTags() {
        return TAGS;
    }

}
