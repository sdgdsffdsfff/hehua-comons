/**
 * 
 */
package com.hehua.commons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhihua
 *
 */
public class ValidationUtils {

    private static final Pattern mobilePattern = Pattern.compile("^1(3|4|5|7|8)[0-9]{9}$");

    private static final Pattern emailPattern = Pattern.compile(
            "[\\w\\.\\-]+@([\\w\\-]+\\.)+[a-zA-Z]+", Pattern.CASE_INSENSITIVE);

    private static final Pattern postCodePattern = Pattern.compile("^[0-9]{6}$");

    public static boolean isValidMobile(String mobile) {
        boolean returnValue = false;

        if (mobile != null && mobile.length() > 0) {
            Matcher m = mobilePattern.matcher(mobile);
            if (m.find()) {
                returnValue = true;
            }
        }
        return returnValue;
    }

    public static boolean isValidEmail(String email) {
        if (StringUtils.isNotEmpty(email)) {
            Matcher matcher = emailPattern.matcher(email);
            return matcher.matches();
        } else {
            return false;
        }
    }

    public static boolean isValidPostCode(String postCode) {
        if (StringUtils.isNotEmpty(postCode)) {
            Matcher matcher = postCodePattern.matcher(postCode);
            return matcher.matches();
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否中文字
     * 
     * @param s String
     * @return 包含中文字符返回true,否则返回false
     */
    public static boolean isValidChinese(String s) {
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (!s.substring(i).matches("[\u4e00-\u9fa5]+")) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符是否中文字
     */
    public static boolean isValidChinese(char word) {
        if ((word >= 0x4e00) && (word <= 0x9fbb)) {
            return true;
        } else {
            return false;
        }
    }

}
