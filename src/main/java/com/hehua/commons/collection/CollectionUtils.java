/**
 * 
 */
package com.hehua.commons.collection;

import java.util.Collections;
import java.util.List;

/**
 * @author zhihua
 *
 */
public class CollectionUtils {

    public static <T> List<T> subList(List<T> list, int offset, int limit) {

        if (list == null || list.size() <= 0) {
            return Collections.emptyList();
        }

        if (offset >= list.size()) {
            return Collections.emptyList();
        }

        offset = Math.max(0, offset);
        limit = Math.max(1, limit);

        int start = offset;
        int end = Math.min(offset + limit, list.size());
        return list.subList(start, end);
    }

    public static <T> boolean isEmpty(List<T> list) {
        return (list == null || list.size() <= 0) ? true : false;
    }
}
