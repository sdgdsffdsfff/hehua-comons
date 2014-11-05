package com.hehua.commons.utils;

import com.hehua.commons.lang.StringUtils;
import com.hehua.commons.time.DateUtils;

/**
 * Created by liuweiwei on 14-7-28.
 */
public class IPUtil {

    /**
     * 将字符串ip转换成长整形
     * @param strIp
     * @return
     */
    public static long ip2long(String strIp){
        String[] strs = strIp.split("\\.");
        if(strs.length != 4){
            return 0;
        }
        long ip = 0;
        for(int i = 0; i < 4; i++){
            int t = Integer.parseInt(strs[i]);
            ip += t * (1L << (3 - i) * 8);
        }
        return ip;
    }
    /**
     * 将长整形ip转换成字符串
     * @param longIp
     * @return
     */
    public static String long2ip(long longIp){
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }

    public static void main(String[] args) {
        /*String strIp = "200.10.1.1";
        long longIp = IPUtil.ip2long(strIp);
        System.out.println(longIp);
        System.out.println(IPUtil.long2ip(longIp));
        String str = "7,4,3,2,5,6,1";
        System.out.println(StringUtils.sortIntString(str));
        */
        long t = 1409384760;
        System.out.println(DateUtils.isToday(t, false));
    }
}
