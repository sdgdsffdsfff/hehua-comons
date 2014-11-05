/**
 * 
 */
package com.hehua.commons;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * 
 * @author zhouzhihua <zhihua@afanda.com>
 * @version 1.0 create at Sep 5, 2012 9:02:12 PM
 */
public abstract class Extractors {

    public static <V, K> Set<K> extractAsSet(Collection<V> values, Function<V, K> extractor) {
        if (CollectionUtils.isEmpty(values)) {
            return Collections.emptySet();
        }

        Set<K> result = Sets.newHashSet();
        for (V v : values) {
            K k = extractor.apply(v);
            if (k == null) {
                continue;
            }
            result.add(k);
        }
        return result;
    }

    public static <V, K> List<K> extractAsList(Collection<V> values, Function<V, K> extractor) {
        if (CollectionUtils.isEmpty(values)) {
            return Collections.emptyList();
        }

        List<K> result = Lists.newArrayListWithExpectedSize(values.size());
        for (V v : values) {
            K k = extractor.apply(v);
            if (k == null) {
                continue;
            }
            result.add(k);
        }
        return result;
    }

    public static <V, K> Set<K> extractOneToManyMapAsSet(Map<?, ? extends Collection<V>> values,
            Function<V, K> extractor) {
        if (values == null || values.isEmpty()) {
            return Collections.emptySet();
        }

        Set<K> result = Sets.newHashSet();
        for (Map.Entry<?, ? extends Collection<V>> entry : values.entrySet()) {
            Collection<V> list = entry.getValue();
            if (CollectionUtils.isNotEmpty(list)) {
                for (V v : list) {
                    K k = extractor.apply(v);
                    if (k == null) {
                        continue;
                    }
                    result.add(k);
                }
            }
        }
        return result;
    }

    public static <V, K> Set<K> extractMultiMapAsSet(Multimap<?, V> values, Function<V, K> extractor) {
        if (values == null || values.isEmpty()) {
            return Collections.emptySet();
        }

        Set<K> result = Sets.newHashSet();
        for (Map.Entry<?, V> entry : values.entries()) {
            V v = entry.getValue();
            K k = extractor.apply(v);
            if (k == null) {
                continue;
            }
            result.add(k);
        }
        return result;
    }

    public static <V, K> List<K> extractMultiMapAsList(Multimap<?, V> values,
            Function<V, K> extractor) {
        if (values == null || values.isEmpty()) {
            return Collections.emptyList();
        }

        List<K> result = Lists.newArrayList();
        for (Map.Entry<?, V> entry : values.entries()) {
            V v = entry.getValue();
            K k = extractor.apply(v);
            if (k == null) {
                continue;
            }
            result.add(k);
        }
        return result;
    }

    public static <V, K> List<K> extractOneToManyMapAsList(Map<?, ? extends Collection<V>> values,
            Function<V, K> extractor) {
        if (values == null || values.isEmpty()) {
            return Collections.emptyList();
        }

        List<K> result = Lists.newArrayList();
        for (Map.Entry<?, ? extends Collection<V>> entry : values.entrySet()) {
            Collection<V> list = entry.getValue();
            if (CollectionUtils.isNotEmpty(list)) {
                for (V v : list) {
                    K k = extractor.apply(v);
                    if (k == null) {
                        continue;
                    }
                    result.add(k);
                }
            }
        }
        return result;
    }

}
