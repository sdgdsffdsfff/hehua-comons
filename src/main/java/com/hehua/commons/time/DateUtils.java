/**
 * 
 */
package com.hehua.commons.time;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 
 * @author zhouzhihua <zhihua@afanda.com>
 * @version 1.0 create at Sep 6, 2012 3:24:20 PM
 */
public abstract class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static Set<Integer> compareFields = new TreeSet<Integer>(Arrays.asList(Calendar.YEAR,
            Calendar.MONTH, Calendar.DATE, Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND,
            Calendar.MILLISECOND));

    public static String formatDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static String formatTimestamp(long time, boolean milisec) {
        long timestamp = 0;
        if (!milisec) {
            timestamp = time * 1000;
        } else {
            timestamp = time;
        }
        Timestamp ts = new Timestamp(timestamp);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(ts);
    }

    public static Date formatStrToDate(String currDate) {
        return getFormatDate(currDate, "yyyy-MM-dd");
    }

    /***
     * 得到日期的前几天还是后几天的日期，具体由参数offset决定
     *
     * @param date
     *            对应的日期
     * @param offset
     *            负数代表date的前几天，整数代表date的后几天
     * @return 返回一个日期型
     */
    public static Date addDatesToDate(Date date, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, offset);
        return calendar.getTime();
    }

    /**
     * 将参数currDate字符串格式化的成日期型，格式化的格式：参数format决定
     *
     * @param currDate 要格式化的日期字符串
     * @param format 格式化的格式如："yyyy-MM-dd"，"yyyy-MM-dd HH:mm:ss"
     *
     * @return 格式化的日期Date
     */
    public static Date getFormatDate(String currDate, String format) {
        if (currDate == null) return null;
        SimpleDateFormat dtFormatdB = null;
        Date date;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            date = dtFormatdB.parse(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat("yyyy-MM-dd");
            Date date_2_;
            try {
                date_2_ = dtFormatdB.parse(currDate);
            } catch (Exception exception) {
                return null;
            }
            return date_2_;
        }
        return date;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static int unix_timestamp() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    public static int getUnitxTimestamp(Date time) {
        return Math.round((int) (time.getTime() / 1000L));
    }

    public static boolean isToday(long timestamp, boolean milisec) {
        Date now = new Date();
        long start = DateUtils.getDateStartTime(now).getTime();
        long end = DateUtils.getDateEndTime(now).getTime();
        if (!milisec) {
            timestamp = timestamp * 1000;
        }
        return timestamp >= start && timestamp <= end;
    }

    public static long intervalMillis(Date date1, Date date2) {
        return date1.getTime() - date2.getTime();
    }

    public static long intervalSeconds(Date date1, Date date2) {
        return TimeUnit.MILLISECONDS.toSeconds(intervalMillis(date1, date2));
    }

    public static long intervalMintues(Date date1, Date date2) {
        return TimeUnit.MILLISECONDS.toMinutes(intervalMillis(date1, date2));
    }

    public static long intervalHours(Date date1, Date date2) {
        return TimeUnit.MILLISECONDS.toHours(intervalMillis(date1, date2));
    }

    public static long intervalDays(Date date1, Date date2) {
        return TimeUnit.MILLISECONDS.toDays(intervalMillis(date1, date2));
    }

    public static long intervalMillis(Calendar calendar1, Calendar calendar2) {
        return calendar1.getTimeInMillis() - calendar2.getTimeInMillis();
    }

    public static long intervalSeconds(Calendar calendar1, Calendar calendar2) {
        return TimeUnit.MILLISECONDS.toSeconds(intervalMillis(calendar1, calendar2));
    }

    public static long intervalMintues(Calendar calendar1, Calendar calendar2) {
        return TimeUnit.MILLISECONDS.toMinutes(intervalMillis(calendar1, calendar2));
    }

    public static long intervalHours(Calendar calendar1, Calendar calendar2) {
        return TimeUnit.MILLISECONDS.toHours(intervalMillis(calendar1, calendar2));
    }

    public static long intervalDays(Calendar calendar1, Calendar calendar2) {
        return TimeUnit.MILLISECONDS.toDays(intervalMillis(calendar1, calendar2));
    }

    public static String getTimeText(Date lastTime) {
        Date nowTime = new Date();
        long interval = nowTime.getTime() - lastTime.getTime();
        if (interval < TimeUnit.MINUTES.toMillis(1)) {
            return "刚刚";
        } else if (interval < TimeUnit.HOURS.toMillis(1)) {
            return TimeUnit.MILLISECONDS.toMinutes(interval) + "分钟前";
        } else if (interval < TimeUnit.DAYS.toMillis(1)) {
            return TimeUnit.MILLISECONDS.toHours(interval) + "小时前";
        } else if (interval < TimeUnit.DAYS.toMillis(4)) {
            return TimeUnit.MILLISECONDS.toDays(interval) + "天前";
        } else {
            return DateFormatUtils.format(lastTime, "MM月dd日");
        }
    }

    public static String getTimeText(long interval) {
        if (interval < TimeUnit.MINUTES.toMillis(1)) {
            return "刚刚";
        } else if (interval < TimeUnit.HOURS.toMillis(1)) {
            return TimeUnit.MILLISECONDS.toMinutes(interval) + "分钟";
        } else if (interval <= TimeUnit.DAYS.toMillis(1)) {
            long hour = TimeUnit.MILLISECONDS.toHours(interval);
            interval = interval - TimeUnit.HOURS.toMillis(hour);
            long minute = TimeUnit.MILLISECONDS.toMinutes(interval);
            String timeText = hour + "小时";
            if (minute > 0) {
                timeText = timeText + minute + "分钟";
            }
            return timeText;
        } else {
            long day = TimeUnit.MILLISECONDS.toDays(interval);
            interval = interval - TimeUnit.DAYS.toMillis(day);
            long hour = TimeUnit.MILLISECONDS.toHours(interval);
            interval = interval - TimeUnit.HOURS.toMillis(hour);
            long minute = TimeUnit.MILLISECONDS.toMinutes(interval);
            String timeText = day + "天";
            if (hour > 0) {
                timeText = timeText + hour + "小时";
            }
            if (minute > 0) {
                timeText = timeText + minute + "分钟";
            }
            return timeText;
        }
    }

    public static Date getDateStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return org.apache.commons.lang3.time.DateUtils.truncate(calendar, Calendar.DATE).getTime();
    }

    public static Date getDateEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar truncateCalendar = org.apache.commons.lang3.time.DateUtils.truncate(calendar,
                Calendar.DATE);
        truncateCalendar.add(Calendar.DATE, 1);
        truncateCalendar.add(Calendar.MILLISECOND, -1);
        return truncateCalendar.getTime();
    }

    public static Calendar addYears(Calendar date, int years) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date.getTimeInMillis());
        c.add(Calendar.YEAR, years);
        return c;
    }

    public static int compareAccurateToFied(Date start, Date end, int field) {
        Calendar startCalendar = getCalendar(start);
        Calendar endCalendar = getCalendar(end);
        return compareTimeAccurateTo(startCalendar, endCalendar, field);
    }

    public static String getBabbyStage(String gender, Date birthday) {
        Date currDate = new Date();
        int month = compareAccurateToFied(currDate, birthday, Calendar.MONTH);
        int year = compareAccurateToFied(currDate, birthday, Calendar.YEAR);
        if (month < 0) {
            month = 12 + month;
            year = year - 1;
        }

        return String.format("%s宝宝%d年%d月", gender, year, month);
    }

    private static int compareTimeAccurateTo(Calendar startCalendar, Calendar endCalendar,
            Integer field) {
        Iterator<Integer> iter = compareFields.iterator();
        while (iter.hasNext()) {
            Integer compareField = iter.next();
            if (field.compareTo(compareField) == 0) {
                return compareDateWithField(startCalendar, endCalendar, compareField);
            }
        }
        return 0;
    }

    private static int compareDateWithField(Calendar startCalendar, Calendar endCalendar,
            Integer compareField) {
        return startCalendar.get(compareField) - endCalendar.get(compareField);
    }

    private static Calendar getCalendar(Date start) {
        Calendar result = Calendar.getInstance();
        result.setTime(start);
        return result;
    }

    /**
     * 是不是同一天
     * 
     * @param date
     * @return
     */
    public static boolean isSameToday(Date date) {
        Calendar currentCalendar = getCalendar(new Date());
        Calendar calendar = getCalendar(date);
        return (currentCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR))
                && (currentCalendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH))
                && (currentCalendar.get(Calendar.DATE) == calendar.get(Calendar.DATE));
    }

    /**
     * date 时间小于当前时间就返回false
     *
     * @param date
     * @return
     * @deprecated 这个方法含义不明确，不要使用
     */
    @Deprecated()
    public static boolean isBefore(Date date) {
        date = truncate(date, Calendar.HOUR);
        return new Date().getTime() > date.getTime();
    }

    public static Date setFixedDateByHour(Date time, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Calendar truncateCalendar = org.apache.commons.lang3.time.DateUtils.truncate(calendar,
                Calendar.HOUR);
        truncateCalendar.set(Calendar.HOUR, 10);
        return truncateCalendar.getTime();
    }

    public static boolean isDateExistRangeDate(Date date, Date startTime, Date endTime){
        if (endTime.before(startTime)) {
            throw new RuntimeException("endTime is greater than startTime");
        }
        if (date.before(startTime) || date.after(endTime)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Date date = getFormatDate("2014-11-02 18:58:00" ,"yyyy-MM-dd HH:mm:ss");

//        System.out.println(formatDateTime(addDatesToDate(date, 0)));
//        System.out.println(formatDateTime(addDatesToDate(date, 1)));
//        System.out.println(formatDateTime(addDatesToDate(date, -1)));
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        Calendar truncateCalendar = org.apache.commons.lang3.time.DateUtils.truncate(calendar,
//                Calendar.HOUR);
//        truncateCalendar.set(Calendar.HOUR, 10);
//
//        System.out.println(formatDateTime(truncateCalendar.getTime()));
//
//        System.out.println(compareAccurateToFied(new Date(), date, Calendar.HOUR));

        Date startDate = addDatesToDate(date,1);
        Date endDate = addDatesToDate(date,3);


        System.out.println(isDateExistRangeDate(new Date(), startDate, endDate));

        System.out.println(isDateExistRangeDate(new Date(), endDate, startDate));




    }
}
