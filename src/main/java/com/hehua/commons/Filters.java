/**
 * 
 */
package com.hehua.commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Predicate;

/**
 * 
 * @author zhouzhihua <zhihua@afanda.com>
 * @version 1.0 create at Oct 23, 2012 11:40:39 AM
 */
public class Filters {

    public static <T> List<T> filter(final List<T> list, Predicate<T> predicate) {
        List<T> filterList = null;

        if (list instanceof ArrayList) {
            for (int i = 0; i < list.size(); i++) {
                T t = list.get(i);
                if (predicate.apply(t)) {
                    if (filterList == null) {
                        filterList = new ArrayList<>(list.size());
                    }
                    filterList.add(t);
                }
            }
        } else {
            for (T t : list) {
                if (predicate.apply(t)) {
                    if (filterList == null) {
                        filterList = new ArrayList<>(list.size());
                    }
                    filterList.add(t);
                }
            }
        }

        if (filterList == null) {
            return Collections.emptyList();
        }
        return filterList;
    }

    public static <T> Set<T> filter(final Set<T> list, Predicate<T> predicate) {
        Set<T> filterSet = null;
        for (T t : list) {
            if (predicate.apply(t)) {

                if (filterSet == null) {
                    filterSet = new HashSet<>(list.size());
                }

                filterSet.add(t);
            }
        }

        if (filterSet == null) {
            return Collections.emptySet();
        }

        return filterSet;
    }

    public static <T> List<T> filter(final Collection<T> list, Predicate<T> predicate) {
        List<T> filterList = null;
        for (T t : list) {
            if (predicate.apply(t)) {
                if (filterList == null) {
                    filterList = new ArrayList<>(list.size());
                }
                filterList.add(t);
            }
        }

        if (filterList == null) {
            return Collections.emptyList();
        }

        return filterList;
    }

    public static <T> T find(final Collection<T> collection, Predicate<T> predicate) {
        return find(collection, predicate, null);
    }

    public static <T> T find(final Collection<T> collection, Predicate<T> predicate, T defaultValue) {
        for (T t : collection) {
            if (predicate.apply(t)) {
                return t;
            }
        }

        return defaultValue;
    }
}
