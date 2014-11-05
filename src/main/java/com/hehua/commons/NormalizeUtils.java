/**
 * 
 */
package com.hehua.commons;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhihua
 *
 */
public class NormalizeUtils {

    private final static int MOBILENUM_LENGTH = 11;

    private final static Set<Integer> MOBILENUM_SEGMENT = new HashSet<>();

    private final static Set<String> NUMBER_IGNORE = new HashSet<>();
    static {
        //加移动号段
        MOBILENUM_SEGMENT.addAll(Arrays.asList(139, 138, 137, 136, 135, 134, 147, 150, 151, 152,
                157, 158, 159, 182, 183, 184, 187, 188));
        //联通号段
        MOBILENUM_SEGMENT.addAll(Arrays.asList(130, 131, 132, 155, 156, 185, 186, 145));
        //电信号段
        MOBILENUM_SEGMENT.addAll(Arrays.asList(133, 153, 180, 181, 189));
        // 未知号段
        MOBILENUM_SEGMENT.addAll(Arrays.asList(140, 141, 142, 143, 144, 146, 148, 149, 154));

        // 黑名单
        NUMBER_IGNORE.add("13800138000");
        NUMBER_IGNORE.add("13333333333");
        NUMBER_IGNORE.add("13888888888");
        NUMBER_IGNORE.add("13901234567");
        NUMBER_IGNORE.add("13000000000");
        NUMBER_IGNORE.add("13800000000");
        NUMBER_IGNORE.add("18888888888");
    }

    /**
     * 格式化大陆手机号码
     * 
     * @param mobile
     * @return 返回大陆手机号格式，11位数字，格式不正确返回null
     */
    public static String normalizeMobile(String mobile) {
        mobile = StringUtils.trim(mobile);

        if (mobile != null) {
            mobile = toDBC(mobile);
            char[] charArray = mobile.toCharArray();
            String numeric = "";
            for (char c : charArray) {
                if (Character.isDigit(c)) {
                    numeric += c;
                }
            }
            if (numeric.length() >= MOBILENUM_LENGTH) {
                numeric = StringUtils.substring(numeric, numeric.length() - MOBILENUM_LENGTH,
                        numeric.length());
                //判断号段
                String segment = StringUtils.substring(numeric, 0, 3);
                if (MOBILENUM_SEGMENT.contains(Integer.valueOf(segment))) {
                    return numeric;
                }
            }
        }
        return null;
    }

    /**
     * 全角转半角
     * 
     * @param input String.
     * @return 半角字符串
     */
    private static String toDBC(String input) {

        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if ((c[i] > '\uFF00') && (c[i] < '\uFF5F')) {
                c[i] = (char) (c[i] - 65248);

            }
        }
        String returnString = new String(c);

        return returnString;
    }
}
