package com.hehua.commons.security.web.whitelist;

import java.util.Collections;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.hehua.commons.tuple.FourTuple;
import com.hehua.commons.tuple.Tuple;

class PublisherWhiteList implements WhiteList, HTMLSupport {

    private final Multimap<String, FourTuple<String, String, Boolean, Set<String>>> TAGS = HashMultimap
            .create();

    private static Set<String> classProperties = Sets.newHashSet();

    protected PublisherWhiteList() {
        TAGS.put("p", Tuple.tuple("class", (String) null, Boolean.FALSE, classProperties));
        TAGS.put("ul", Tuple.tuple("class", (String) null, Boolean.FALSE, classProperties));
        TAGS.put("ol", Tuple.tuple("class", (String) null, Boolean.FALSE, classProperties));
        TAGS.put("li", Tuple.tuple("class", (String) null, Boolean.FALSE, classProperties));
        TAGS.put("br", Tuple.tuple("class", (String) null, Boolean.FALSE, classProperties));
        TAGS.put("strong", Tuple.tuple("class", (String) null, Boolean.FALSE, classProperties));
        TAGS.put("img",
                Tuple.tuple("fid", (String) null, Boolean.FALSE, Collections.<String> emptySet()));
        TAGS.put("img",
                Tuple.tuple("id", (String) null, Boolean.FALSE, Collections.<String> emptySet()));
        TAGS.put("img",
                Tuple.tuple("src", (String) null, Boolean.TRUE, Collections.<String> emptySet()));
        TAGS.put("img",
                Tuple.tuple("alt", (String) null, Boolean.FALSE, Collections.<String> emptySet()));
        TAGS.put("img",
                Tuple.tuple("title", (String) null, Boolean.FALSE, Collections.<String> emptySet()));
        TAGS.put("u", null);
        TAGS.put("em", null);
        TAGS.put("strike", null);
        TAGS.put("blockquote", null);
        TAGS.put("code", null); // 这里code需要进行处理
        TAGS.put("h1", null);
        TAGS.put("h2", null);
        TAGS.put("h3", null);
        TAGS.put("h4", null);
        TAGS.put("h5", null);
        TAGS.put("h6", null);
        //        TAGS.put("a", Tuple.tuple("href", "http", Boolean.TRUE, Collections.<String> emptySet()));
        //        TAGS.put("a", Tuple.tuple("href", "https", Boolean.TRUE, Collections.<String> emptySet()));
        //        TAGS.put("a", Tuple.tuple("href", "ftp", Boolean.TRUE, Collections.<String> emptySet()));
        //        TAGS.put("a", Tuple.tuple("href", "mailto", Boolean.TRUE, Collections.<String> emptySet()));
        //        TAGS.put("a", Tuple.tuple("href", "weixin", Boolean.TRUE, Collections.<String> emptySet()));
        //        TAGS.put("a", Tuple.tuple("target", (String) null, Boolean.FALSE,
        //                Collections.<String> emptySet()));
        //        TAGS.put("a",
        //                Tuple.tuple("title", (String) null, Boolean.FALSE, Collections.<String> emptySet()));
        //        TAGS.put("a", Tuple.tuple("class", (String) null, Boolean.FALSE, classProperties));
        TAGS.put("hr", Tuple.tuple("class", (String) null, Boolean.FALSE, classProperties));
        TAGS.put("span", Tuple.tuple("class", (String) null, Boolean.FALSE, classProperties));
        TAGS.put("pre", Tuple.tuple("config", (String) null, Boolean.FALSE,
                Collections.<String> emptySet()));
    }

    @Override
    public Multimap<String, FourTuple<String, String, Boolean, Set<String>>> getTags() {
        return TAGS;
    }
}
