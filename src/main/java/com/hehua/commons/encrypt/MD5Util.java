package com.hehua.commons.encrypt;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 采用MD5加密
 * @author hewenjerry
 */
public class MD5Util {

    public static String MD5Encrypt(String inStr) {

            // http://stackoverflow.com/questions/1057041/difference-between-java-and-php5-md5-hash
            // http://code.google.com/p/roboguice/issues/detail?id=89
            try {

                final byte[] hash = MessageDigest.getInstance("MD5").digest(inStr.getBytes("UTF-8"));
                final StringBuilder hashString = new StringBuilder();

                for (byte aHash : hash) {
                    String hex = Integer.toHexString(aHash);

                    if (hex.length() == 1) {
                        hashString.append('0');
                        hashString.append(hex.charAt(hex.length() - 1));
                    } else {
                        hashString.append(hex.substring(hex.length() - 2));
                    }
                }

                return hashString.toString();

            } catch (Exception e) {
                return inStr;
            }
    }

    public static String bytetoString(byte[] digest) {

        String str = "";
        String tempStr = "";
        for (int i = 1; i < digest.length; i++) {
            tempStr = (Integer.toHexString(digest[i] & 0xff));
            if (tempStr.length() == 1) {
                str = str + "0" + tempStr;
            }
            else {
                str = str + tempStr;
            }
        }
        return str.toLowerCase();

    }

    // 测试主函数
    public static void main(String args[]) {
        String s = new String("tangfuqianghsdfiwgfeipuw还给 iu 批评和地方wfgiw俄风格风IE给我");
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + MD5Encrypt(s));

    }
}
