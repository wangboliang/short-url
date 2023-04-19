package com.shorturl.util;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    public static final String YYYY = "yyyy";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMM = "yyyyMM";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDDHH = "yyyyMMddHH";
    public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String YYYY$MM = "yyyy.MM";
    public static final String YYYY$MM$DD = "yyyy.MM.dd";
    public static final String YYYY$MM$DD_HH_MM = "yyyy.MM.dd HH:mm";
    public static final String YYYY$MM$DD_HH_MM_SS = "yyyy.MM.dd HH:mm:ss";
    private static final String[] MONTH_ZH = new String[]{"十二月", "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月"};

    public static long getTime(Date date) {
        if (date == null) {
            return 0;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis() / 1000;
    }

    public static long getZeroTime(Date date) {
        if (date == null) {
            return 0;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 时分秒归零
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    public static Date getZeroDate(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        return zero;
    }

    public static Date addYear(Date date, int year) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    public static Date addQuarter(Date date, int quarter) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, quarter * 3);
        return calendar.getTime();
    }

    public static Date addMonth(Date date, int month) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public static Date addDayOfYear(Date date, int day) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    public static Date addHourOfDay(Date date, int hour) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    public static Date addMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    public static long addSecondOfYear(long nowTime, int year) {
        if (nowTime <= 0) {
            return 0;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(nowTime * 1000);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTimeInMillis() / 1000;
    }

    public static long addSecondOfMonth(long nowTime, int month) {
        if (nowTime <= 0) {
            return 0;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(nowTime * 1000);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTimeInMillis() / 1000;
    }

    public static long addSecondOfDay(long nowTime, int day) {
        if (nowTime <= 0) {
            return 0;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(nowTime * 1000);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTimeInMillis() / 1000;
    }

    public static String formatDateToString(Date date, String pattern) {
        if (date == null || StringUtils.isEmpty(pattern)) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Date formatDate(String value, String pattern) {
        if (StringUtils.isEmpty(value) || StringUtils.isEmpty(pattern)) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(value);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 比较两个年份大小
     *
     * @param dateStr1 yyyy
     * @param dateStr2 yyyy
     * @return
     */
    public static boolean beforeYear(String dateStr1, String dateStr2) {
        if (StringUtils.isEmpty(dateStr1) || StringUtils.isEmpty(dateStr2)) {
            return false;
        }

        Date date1 = formatDate(dateStr1, YYYY);
        Date date2 = formatDate(dateStr2, YYYY);
        return date1.before(date2);
    }

    /**
     * 比较两个月份大小
     *
     * @param dateStr1 yyyyMM
     * @param dateStr2 yyyyMM
     * @return
     */
    public static boolean beforeMonth(String dateStr1, String dateStr2) {
        if (StringUtils.isEmpty(dateStr1) || StringUtils.isEmpty(dateStr2)) {
            return false;
        }

        Date date1 = formatDate(dateStr1, YYYYMM);
        Date date2 = formatDate(dateStr2, YYYYMM);
        return date1.before(date2);
    }

    /**
     * 比较两个日份大小
     *
     * @param dateStr1 yyyyMMdd
     * @param dateStr2 yyyyMMdd
     * @return
     */
    public static boolean beforeDay(String dateStr1, String dateStr2) {
        if (StringUtils.isEmpty(dateStr1) || StringUtils.isEmpty(dateStr2)) {
            return false;
        }

        Date date1 = formatDate(dateStr1, YYYYMMDD);
        Date date2 = formatDate(dateStr2, YYYYMMDD);
        return date1.before(date2);
    }

    /**
     * 比较两个日份大小
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean beforeDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        return date1.before(date2);
    }

    /**
     * 判断日期date1是否大于date2 比较yyyyMMdd大小
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isGreaterThan(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        int dateInt1 = Integer.parseInt(DateUtil.formatDateToString(date1, DateUtil.YYYYMMDD));
        int dateInt2 = Integer.parseInt(DateUtil.formatDateToString(date2, DateUtil.YYYYMMDD));
        return dateInt1 > dateInt2;
    }

    /**
     * 判断日期date1是否小于date2 比较yyyyMMdd大小
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isLessThan(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        int dateInt1 = Integer.parseInt(DateUtil.formatDateToString(date1, DateUtil.YYYYMMDD));
        int dateInt2 = Integer.parseInt(DateUtil.formatDateToString(date2, DateUtil.YYYYMMDD));
        return dateInt1 < dateInt2;
    }

    /**
     * 判断日期date1是否等于date2 比较yyyyMMdd大小
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isEqual(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        int dateInt1 = Integer.parseInt(DateUtil.formatDateToString(date1, DateUtil.YYYYMMDD));
        int dateInt2 = Integer.parseInt(DateUtil.formatDateToString(date2, DateUtil.YYYYMMDD));
        return dateInt1 == dateInt2;
    }

    /**
     * 判断日期是否在生效日期内 比较yyyyMMdd大小，含头不含尾
     *
     * @param date      下一周期执行日期
     * @param startDate 生效时间
     * @param endDate   失效时间
     * @return
     */
    public static boolean isWithin(Date date, Date startDate, Date endDate) {
        if (date == null || startDate == null || endDate == null) {
            return false;
        }

        int dateInt = Integer.parseInt(DateUtil.formatDateToString(date, DateUtil.YYYYMMDD));
        int startDateInt = Integer.parseInt(DateUtil.formatDateToString(startDate, DateUtil.YYYYMMDD));
        int endDateInt = Integer.parseInt(DateUtil.formatDateToString(endDate, DateUtil.YYYYMMDD));
        return (dateInt > startDateInt || dateInt == startDateInt) && (dateInt < endDateInt);
    }

    /**
     * 获取两个年份区间列表（降序排序），包括开始结束年份
     *
     * @param beginYear 开始年份 yyyy
     * @param endYear   结束年份 yyyy
     * @return 年份列表 yyyy
     */
    public static List<String> getBetweenYearList(String beginYear, String endYear) {
        List<String> yearList = new ArrayList<String>();
        if (StringUtils.isEmpty(beginYear) || StringUtils.isEmpty(endYear) ||
                (!beginYear.equals(beginYear) && !beforeYear(beginYear, endYear))) {
            return yearList;
        }

        Calendar begin = Calendar.getInstance();
        begin.setTime(formatDate(beginYear, YYYY));

        Calendar end = Calendar.getInstance();
        end.setTime(formatDate(endYear, YYYY));
        while (end.after(begin)) {
            yearList.add(formatDateToString(end.getTime(), YYYY));
            end.add(Calendar.YEAR, -1);
        }
        yearList.add(beginYear);
        return yearList;
    }

    /**
     * 获取两个月份区间列表（降序排序），包括开始结束月份
     *
     * @param beginMonth 开始月份 yyyyMM
     * @param endMonth   结束月份 yyyyMM
     * @return 月份列表 yyyyMM
     */
    public static List<String> getBetweenMonthList(String beginMonth, String endMonth) {
        List<String> monthList = new ArrayList<String>();
        if (StringUtils.isEmpty(beginMonth) || StringUtils.isEmpty(endMonth) ||
                (!beginMonth.equals(endMonth) && !beforeMonth(beginMonth, endMonth))) {
            return monthList;
        }

        Calendar begin = Calendar.getInstance();
        begin.setTime(formatDate(beginMonth, YYYYMM));

        Calendar end = Calendar.getInstance();
        end.setTime(formatDate(endMonth, YYYYMM));
        while (end.after(begin)) {
            monthList.add(formatDateToString(end.getTime(), YYYYMM));
            end.add(Calendar.MONTH, -1);
        }
        monthList.add(beginMonth);
        return monthList;
    }

    /**
     * 获取两个日份区间列表（降序排序），包括开始结束日份
     *
     * @param beginDay 开始日份 yyyyMMdd
     * @param endDay   结束日份 yyyyMMdd
     * @return 日份列表 yyyyMMdd
     */
    public static List<String> getBetweenDayList(String beginDay, String endDay) {
        List<String> dayList = new ArrayList<String>();
        if (StringUtils.isEmpty(beginDay) || StringUtils.isEmpty(endDay) ||
                (!beginDay.equals(endDay) && !beforeDay(beginDay, endDay))) {
            return dayList;
        }

        Calendar begin = Calendar.getInstance();
        begin.setTime(formatDate(beginDay, YYYYMMDD));

        Calendar end = Calendar.getInstance();
        end.setTime(formatDate(endDay, YYYYMMDD));
        while (end.after(begin)) {
            dayList.add(formatDateToString(end.getTime(), YYYYMMDD));
            end.add(Calendar.DAY_OF_YEAR, -1);
        }
        dayList.add(beginDay);
        return dayList;
    }

    /**
     * 时间戳秒转换成日期字符串
     *
     * @param timeSecond 要转换的时间戳秒
     * @param df
     * @return 返回指定格式的时间字符串
     */
    public static String convertTimeSecond2DateStr(long timeSecond, String df) {
        DateFormat format = new SimpleDateFormat(df);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeSecond);
        return format.format(timeSecond);
    }

    /**
     * 时间戳秒转换成日期字符串
     *
     * @param timeSecond 要转换的时间戳秒
     * @return 返回yyyyMMddHHmm的时间字符串
     */
    public static String convertTimeSecond2DateStr(long timeSecond) {
        return convertTimeSecond2DateStr(timeSecond, YYYYMMDDHHMM);
    }


    /**
     * 时间戳秒转换成日期字符串
     *
     * @param timeSecond 要转换的时间戳秒
     * @return 返回yyyy-MM-dd HH:mm:ss的时间字符串
     */
    public static String convertTimeSecond3DateStr(Long timeSecond) {
        return convertTimeSecond2DateStr(timeSecond, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取月份第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取月份最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取季度
     *
     * @param date
     * @return
     */
    public static int getQuarter(Date date) {
        if (date == null) {
            return 0;
        }

        int quarter = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        if (month >= 0 && month <= 2) {
            quarter = 1;
        } else if (month >= 3 && month <= 5) {
            quarter = 2;
        } else if (month >= 6 && month <= 8) {
            quarter = 3;
        } else if (month >= 9 && month <= 11) {
            quarter = 4;
        }
        return quarter;
    }

    /**
     * 获取季度第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfQuarter(Date date) {
        if (date == null) {
            return null;
        }

        int firstMonthOfQuarter = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        if (month >= 0 && month <= 2) {
            firstMonthOfQuarter = 2;
        } else if (month >= 3 && month <= 5) {
            firstMonthOfQuarter = 3;
        } else if (month >= 6 && month <= 8) {
            firstMonthOfQuarter = 6;
        } else if (month >= 9 && month <= 11) {
            firstMonthOfQuarter = 9;
        }
        calendar.set(Calendar.MONTH, firstMonthOfQuarter);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取季度最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfQuarter(Date date) {
        if (date == null) {
            return null;
        }

        int lastMonthOfQuarter = 2;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        if (month >= 0 && month <= 2) {
            lastMonthOfQuarter = 2;
        } else if (month >= 3 && month <= 5) {
            lastMonthOfQuarter = 5;
        } else if (month >= 6 && month <= 8) {
            lastMonthOfQuarter = 8;
        } else if (month >= 9 && month <= 11) {
            lastMonthOfQuarter = 11;
        }
        calendar.set(Calendar.MONTH, lastMonthOfQuarter);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取年份第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfYear(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
        return calendar.getTime();
    }

    /**
     * 获取年份最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfYear(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        return calendar.getTime();
    }
}
