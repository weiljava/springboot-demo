/**
 *
 */
package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间工具类
 */
public class DateUtil {

    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String DATE_FORMAT = "yyyy-MM-dd";

    public static String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    public static String DATE_FORMAT_YYYYMM = "yyyyMM";

    public static String DATE_FORMAT_YYYYMMDDhhmmss = "yyyyMMddHHmmss";

    public static String MONDAY = "mon";
    public static String SUNDAY = "sun";

    public static Date parse(String dateStr, String fmtStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fmtStr);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date formateDate(Date date, String fmtStr) {
        String dateS = parseDate(date, fmtStr);
        return parse(dateS, fmtStr);
    }

    /**
     * @param dateValue 要转换的日期
     * @param fmtStr    转换日期的格式, 默认为:"yyyy-MM-dd"
     * @return String 转换之后的字符串
     * @Description: 将日期转换成指定格式的字符串
     */
    public static String parseDate(Date dateValue, String fmtStr) {
        if (dateValue == null) {
            return "";
        }
        if (fmtStr == null || fmtStr.length() == 0) {
            fmtStr = DATE_TIME_FORMAT;
        }
        SimpleDateFormat fmt = new SimpleDateFormat(fmtStr);
        return fmt.format(dateValue);
    }

    /**
     * 将Long转换成指定格式的字符串
     *
     * @param stamp
     * @param fmtStr
     * @return
     */
    public static String parseDate2String(Long stamp, String fmtStr) {
        if (null == stamp) {
            return "";
        }
        if (fmtStr == null || fmtStr.length() == 0) {
            fmtStr = DATE_TIME_FORMAT;
        }
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(stamp);
        SimpleDateFormat dateformat = new SimpleDateFormat(fmtStr);
        return dateformat.format(c.getTime());
    }

    /**
     * @param date
     * @param format
     * @return
     */
    public static long parseDateLong(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date).getTime();
    }

    public static long parseDate2Long(Date date) {
        return date.getTime();
    }

    /**
     * @param dateValue 要转换的日期
     * @param fmtStr    转换日期的格式, 默认为:"yyyy-MM-dd"
     * @return String 转换之后的字符串
     * @Description: 将日期转换成指定格式的字符串
     */
    public static String parseDateByTimeStamp(java.sql.Timestamp dateValue, String fmtStr) {
        if (dateValue == null) {
            return "";
        }
        if (fmtStr == null || fmtStr.length() == 0) {
            fmtStr = DATE_FORMAT;
        }
        SimpleDateFormat fmt = new SimpleDateFormat(fmtStr);
        return fmt.format(dateValue);
    }


    /**
     * 获取昨天的日期
     *
     * @return
     */
    public static Date getYesterday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }


    /**
     * 获取半年前的当月第一天日期
     *
     * @return
     */
    public static Date getHalfyearCurrentMonthFiestDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -5);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 获取去年今天的日期
     *
     * @return
     */
    public static Date getLastYearToday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        return c.getTime();
    }

    /**
     * 获取去年当月第一天的日期
     *
     * @return
     */
    public static Date getLastYearurrentMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.YEAR, -1);
        return c.getTime();
    }

    /**
     * 获取当前月的第一天
     *
     * @return
     */
    public static Date getFristDayByCurrentMonth() {
        //默认当前月
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 获取上月的最后一天
     *
     * @return
     */
    public static Date getLastDayByLastMonth() {
        //默认当前月
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    /**
     * 获取当前月的最后一天
     *
     * @return
     */
    public static Date getLastDayByCurrentMonth() {
        //默认当前月
        Calendar c = Calendar.getInstance();
        int value = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, value);
        return c.getTime();
    }

    /**
     * 获取某个月的第一天数据
     *
     * @param dataValue 时间值
     * @return返回日期类型
     */
    public static Date getFristDay(String dataValue) {
        Date date = DateUtil.parse(dataValue, DATE_FORMAT_YYYYMMDD);
        //创建日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取某个月的最后一天数据
     *
     * @param dataValue 时间值
     * @return返回日期类型
     */
    public static Date getLastDay(String dataValue) {
        Date date = DateUtil.parse(dataValue, DATE_FORMAT_YYYYMMDD);
        //创建日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);     //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        return calendar.getTime();
    }


    /**
     * 得到某个月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
    }

    /**
     * 得到某个月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
    }

    /**
     * 获取本周周一和周日的时间
     *
     * @return
     */
    public static Map<String, String> getWeekDay(String dateFormat) {
        Map<String, String> map = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
        map.put(MONDAY, df.format(cal.getTime()));
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); //获取本周日的日期
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        map.put(SUNDAY, df.format(cal.getTime()));
        return map;
    }


    /**
     * 获取本周第一天的日期
     *
     * @return
     */
    public static Date getFristDayOfCurrentWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
        return cal.getTime();
    }

    public static Date getFirstDayOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1); //获取本周一的日期
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

}
