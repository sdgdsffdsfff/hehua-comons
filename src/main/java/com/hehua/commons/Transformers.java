/**
 * 
 */
package com.hehua.commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

/**
 * 
 * @author zhouzhihua <zhihua@afanda.com>
 * @version 1.0 create at Sep 5, 2012 9:02:55 PM
 */
public abstract class Transformers {

    public static final Function<String, Integer> toIntConverter = new Function<String, Integer>() {

        @Override
        public Integer apply(String f) {
            return Integer.parseInt(f);
        }
    };

    public static <V, K> Map<K, V> transformAsOneToOneMap(Collection<V> values,
            Function<V, K> keyExtractor) {
        if (CollectionUtils.isEmpty(values)) {
            return Collections.emptyMap();
        }

        Map<K, V> result = Maps.newHashMap();
        for (V v : values) {
            K k = keyExtractor.apply(v);
            if (k == null) {
                continue;
            }
            result.put(k, v);
        }
        return result;
    }

    public static <K, SK, V, SV> Map<SK, SV> transformMap(Map<K, V> values,
            Function<K, SK> keyConverter, Function<V, SV> valueConverter) {
        if (values == null || values.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<SK, SV> result = new HashMap<>(values.size());
        for (Map.Entry<K, V> entry : values.entrySet()) {
            SK k = keyConverter.apply(entry.getKey());
            SV v = valueConverter.apply(entry.getValue());
            result.put(k, v);
        }
        return result;
    }

    public static <K, V, SV> Map<K, SV> transformMapValue(Map<K, V> values,
            Function<V, SV> valueConverter) {
        if (values == null || values.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<K, SV> result = new HashMap<>(values.size());
        for (Map.Entry<K, V> entry : values.entrySet()) {
            SV v = valueConverter.apply(entry.getValue());
            result.put(entry.getKey(), v);
        }
        return result;
    }

    public static <K, SK, V> Map<SK, V> transformMapKey(Map<K, V> values,
            Function<K, SK> keyConverter) {
        if (values == null || values.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<SK, V> result = new HashMap<>(values.size());
        for (Map.Entry<K, V> entry : values.entrySet()) {
            result.put(keyConverter.apply(entry.getKey()), entry.getValue());
        }
        return result;
    }

    public static <V, K> Map<K, V> transformAsOneToOneConcurrentMap(Collection<V> values,
            Function<V, K> keyExtractor) {
        if (CollectionUtils.isEmpty(values)) {
            return Maps.newConcurrentMap();
        }

        Map<K, V> result = Maps.newConcurrentMap();
        for (V v : values) {
            K k = keyExtractor.apply(v);
            if (k == null) {
                continue;
            }
            result.put(k, v);
        }
        return result;
    }

    public static <V, K> ListMultimap<K, V> transformAsListMultimap(Collection<V> values,
            Function<V, K> keyExtractor) {
        ListMultimap<K, V> result = Multimaps.newListMultimap(Maps.<K, Collection<V>> newHashMap(),
                Transformers.<V> getArrayListFactory());

        if (CollectionUtils.isEmpty(values)) {
            return result;
        }

        for (V v : values) {
            K k = keyExtractor.apply(v);
            if (k == null) {
                continue;
            }
            result.put(k, v);
        }
        return result;
    }

    public static <V, K> Map<K, List<V>> transformAsListHashmap(Collection<V> values,
            Function<V, K> keyExtractor) {
        Map<K, List<V>> result = new HashMap<>();
        if (CollectionUtils.isEmpty(values)) {
            return result;
        }

        for (V v : values) {
            K k = keyExtractor.apply(v);
            if (k == null) {
                continue;
            }
            List<V> list = result.get(k);
            if (list == null) {
                list = new ArrayList<>();
                result.put(k, list);
            }
            list.add(v);
        }
        return result;
    }

    public static <V, K> SetMultimap<K, V> transformAsSetMultimap(Collection<V> values,
            Function<V, K> keyExtractor) {
        SetMultimap<K, V> result = Multimaps.newSetMultimap(Maps.<K, Collection<V>> newHashMap(),
                Transformers.<V> getHashSetFactory());

        if (CollectionUtils.isEmpty(values)) {
            return result;
        }

        for (V v : values) {
            K k = keyExtractor.apply(v);
            if (k == null) {
                continue;
            }
            result.put(k, v);
        }
        return result;
    }

    private static final Supplier<? extends List<?>> arrayListFactory = new Supplier<List<?>>() {

        @Override
        public List<?> get() {
            return Lists.newArrayList();
        }

    };

    public static <S, T> List<T> transform(S[] array, Function<S, T> function) {
        List<T> result = Lists.newArrayListWithExpectedSize(array.length);
        for (S s : array) {
            result.add(function.apply(s));
        }
        return result;
    }

    public static <S, T> List<T> transformList(List<S> original, Function<S, T> function) {
        List<T> result = Lists.newArrayListWithExpectedSize(original.size());
        for (S s : original) {
            result.add(function.apply(s));
        }
        return result;
    }

    public static <S, T> List<T> transformList(Collection<S> original, Function<S, T> function) {
        List<T> result = Lists.newArrayListWithExpectedSize(original.size());
        for (S s : original) {
            result.add(function.apply(s));
        }
        return result;
    }

    public static <K, V, T> List<T> transformAsList(Map<K, V> original, Function<V, T> function) {
        List<T> result = Lists.newArrayListWithExpectedSize(original.size());
        for (Map.Entry<K, V> entry : original.entrySet()) {
            result.add(function.apply(entry.getValue()));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <V> Supplier<? extends List<V>> getArrayListFactory() {
        return (Supplier<? extends List<V>>) arrayListFactory;
    }

    private static final Supplier<? extends Set<?>> hashSetFactory = new Supplier<Set<?>>() {

        @Override
        public Set<?> get() {
            return Sets.newHashSet();
        }

    };

    @SuppressWarnings("unchecked")
    private static <V> Supplier<? extends Set<V>> getHashSetFactory() {
        return (Supplier<? extends Set<V>>) hashSetFactory;
    }

    public static <K, V> Multimap<K, V> newListMultimap() {
        ListMultimap<K, V> result = Multimaps.newListMultimap(Maps.<K, Collection<V>> newHashMap(),
                Transformers.<V> getArrayListFactory());
        return result;
    }

}
