/**
 * 
 */
package com.hehua.commons.lang;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * 
 * @author zhouzhihua <zhihua@afanda.com>
 * @version 1.0 create at Apr 7, 2013 11:36:53 AM
 */
public class StringUtils {

    public static final char SEPARATOR = ',';

    public static List<String> split(String str, char separatorChar) {
        return Lists.newArrayList(org.apache.commons.lang3.StringUtils.split(str, separatorChar));
    }

    public static String sortIntString(String str) {
        return StringUtils.sortIntString(str, ",");
    }

    public static String sortIntString(String str, String sep) {
        String[] strArr = str.split(sep);
        Arrays.sort(strArr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i]);
            if (i != (strArr.length - 1)) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static List<String> split(String str) {
        return split(str, SEPARATOR);
    }

    public static List<Integer> getIntegerList(String str, char separatorChar) {

        if (org.apache.commons.lang3.StringUtils.isBlank(str)) {
            return Collections.emptyList();
        }

        String[] splits = org.apache.commons.lang3.StringUtils.split(str, separatorChar);
        List<Integer> results = Lists.newArrayListWithExpectedSize(splits.length);
        for (String split : splits) {
            int result = Integer.parseInt(org.apache.commons.lang3.StringUtils.trimToEmpty(split));
            results.add(result);
        }

        return results;
    }

    public static List<Integer> getIntegerList(String str) {
        return getIntegerList(str, SEPARATOR);
    }

    public static Set<Integer> getIntegerSet(String str, char separatorChar) {

        if (org.apache.commons.lang3.StringUtils.isBlank(str)) {
            return Collections.emptySet();
        }

        String[] splits = org.apache.commons.lang3.StringUtils.split(str, separatorChar);
        Set<Integer> results = Sets.newLinkedHashSetWithExpectedSize(splits.length);
        for (String split : splits) {
            int result = Integer.parseInt(org.apache.commons.lang3.StringUtils.trimToEmpty(split));
            results.add(result);
        }
        return results;
    }

    public static Set<Integer> getIntegerSet(String str) {
        return getIntegerSet(str, SEPARATOR);
    }

    public static <T> String join(Collection<T> collection, Function<T, String> toString,
            char separatorChar) {
        Iterable<String> transform = Iterables.transform(collection, toString);
        return org.apache.commons.lang3.StringUtils.join(transform, separatorChar);
    }

    public static <T> String join(Collection<T> collection, Function<T, String> toString,
            String separator) {
        Iterable<String> transform = Iterables.transform(collection, toString);
        return org.apache.commons.lang3.StringUtils.join(transform, separator);
    }

    public static int getChineseLength(String name) {
        if (name == null) {
            return 0;
        }

        int length = 0;
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (isAscii(ch)) {
                length++;
            } else {
                length += 2;
            }
        }
        return length;
    }

    public static boolean isAscii(char ch) {
        return ch >= '\0' && ch <= '\u007f';
    }

    public static boolean isLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') || ch >= 'A' && ch <= 'Z';
    }

    public static boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public static boolean isChinese(char word) {
        if ((word >= 0x4e00) && (word <= 0x9fbb)) {
            return true;
        } else {
            return false;
        }
    }

    public static String abbreviateChinese(String str, int maxWidth) {
        // TODO 针对中文，标点符号做针对性处理
        return org.apache.commons.lang3.StringUtils.abbreviate(str, maxWidth);
    }

    public static void main(String[] args) {
        String str = "你现在在哪里1233，abc？你还好吗";
        System.out.println(abbreviateChinese(str, 10));
    }

}
