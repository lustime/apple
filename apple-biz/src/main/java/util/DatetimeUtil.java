package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 17040407 on 2017/7/6.
 */
public class DatetimeUtil {

    /**
     *时间类常量
     */
    private interface DateFormatConstants {

        /** 日期格式: yyyy-MM-dd HH:mm:ss */
        String DATETIME_PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";

        /** 日期格式: yyyyMMdd */
        String DATETIME_YYYYMMDD = "yyyyMMdd";

        /** 日期格式: HH:mm:ss */
        String TIME_PATTERN_STANDARD = "HH:mm:ss";

        /** 日期格式: yyyy-MM-dd */
        String DATE_PATTERN_STANDARD = "yyyy-MM-dd";

        String TIME_HOUR_MINUTE_PATTERN = "HH:mm";

        /** 时间格式: yyyyMMddHHmmss */
        String DATETIME_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

        /** 时间格式: yyyy-MM-dd HH:mm:ss.SSS */
        String DATETIME_YYYYMMDD_HHMMSS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    }


    /**
     * 日志
     */
    //private static SimpleDateFormat defaultPattern = new SimpleDateFormat(DateFormatConstants.DATETIME_PATTERN_STANDARD);

    /**
     * Formats a Date into a standard-format date string.
     *
     * @param date The date value to be formatted.
     * @return The standard-formatted date string.
     * @author King
     * @date 2013-12-12
     * @since v1.0
     */
    public static String formatDate(Date date) {
        return format(date, DateFormatConstants.DATE_PATTERN_STANDARD);
    }

    public static String formatDateYYYYMMdd(Date date) {
        return format(date, DateFormatConstants.DATETIME_YYYYMMDD);
    }

    /**
     * Formats a Date into a standard-format time string.
     *
     * @param date The date value to be formatted.
     * @return The standard-formatted time string.
     * @author King
     * @date 2013-12-12
     * @since v1.0
     */
    public static String formatTime(Date date) {
        return format(date, DateFormatConstants.TIME_PATTERN_STANDARD);
    }

    /**
     * Formats a Date into a standard-format date-time string.
     *
     * @param date The date value to be formatted.
     * @return The standard-formatted date-time string.
     * @author King
     * @date 2013-12-12
     * @since v1.0
     */
    public static String formatDatetime(Date date) {
        return format(date, DateFormatConstants.DATETIME_PATTERN_STANDARD);
    }

    public static String formatDatetimeYYYYMMddHHmmss(Date date) {
        return format(date, DateFormatConstants.DATETIME_YYYYMMDDHHMMSS);
    }

    /**
     * Formats a Date into a given-pattern string.
     *
     * @param date The date value to be formatted.
     * @param pattern The given pattern.
     * @return The formatted date string.
     * @author King
     * @date 2013-12-12
     * @since v1.0
     */
    public static String format(Date date, String pattern) {
        // NO log.
        if (date == null || StringUtils.isBlank(pattern)) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String formatDate(Long date) {
        return format(date, DateFormatConstants.DATE_PATTERN_STANDARD);
    }

    public static String formatDatetime(Long datetime) {
        return format(datetime, DateFormatConstants.DATETIME_PATTERN_STANDARD);
    }

    public static String format(Long datetime, String pattern) {
        return new SimpleDateFormat(pattern).format(datetime);
    }

    /**
     * Parses a date time string to produce a Date via the given pattern.
     *
     * @param strDate The date string to be parsed.
     * @param pattern The given pattern.
     * @return A Date parsed from the string.
     * @author King
     * @date 2013-12-13
     * @since v1.0
     */
    public static Date parse(String strDate, String pattern) {
        // NO log.
        if (StringUtils.isBlank(strDate) || StringUtils.isBlank(pattern)) {
            return null;
        }

        Date date = null;
        try {
            date = new SimpleDateFormat(pattern).parse(strDate);
        } catch (ParseException e) {
//            LOGGER.error("Failed to format '" + strDate + "' to Date object with pattern '" + pattern + "'!", e);
        }
        return date;
    }

    /**
     * Parses a date time string to produce a Date via the standard date time pattern.
     *
     * @param strDatetime The date time string to be parsed.
     * @return A Date parsed from the string.
     * @author King
     * @date 2013-12-13
     * @since v1.0
     */
    public static Date parseDatetime(String strDatetime) {
        return parse(strDatetime, DateFormatConstants.DATETIME_PATTERN_STANDARD);
    }

    /**
     * Parses a date string to produce a Date via the standard date pattern.
     *
     * @param strDate The date string to be parsed.
     * @return A Date parsed from the string.
     * @author King
     * @date 2013-12-13
     * @since v1.0
     */
    public static Date parseDate(String strDate) {
        return parse(strDate, DateFormatConstants.DATE_PATTERN_STANDARD);
    }

    /**
     * Parses a time string to produce a Date via the standard time pattern.
     *
     * @param strTime The time string to be parsed.
     * @return A Date parsed from the string.
     * @author King
     * @date 2013-12-13
     * @since v1.0
     */
    public static Date parseTime(String strTime) {
        return parse(strTime, DateFormatConstants.TIME_PATTERN_STANDARD);
    }

    /**
     * 计算两个时间点相差的小时数，保留2位小数
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 相差小时数
     * @author jinrong
     * @since v1.0
     */
    public static Double calIntervalHours(Date startTime, Date endTime) {
        if (startTime == null || endTime == null) {
            return null;
        }
        // 相差分钟数
        long minutes = (endTime.getTime() - startTime.getTime()) / 1000 / 60;
        // 小时数
        long hours = minutes / 60;
        // 多余的分钟数
        double additionalHours = minutes % 60 * 1.0 / 60;
        // 四舍五入
        return Math.round((hours + additionalHours) * 100.0) / 100.0;
    }

    /**
     * 返回指定时间所在的小时区间
     *
     * @param date 日期
     * @return 小时区间，0-23
     * @author jinrong
     * @since v3.0
     */
    public static int getHours(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Formats a Date into a HH:mm format string.
     *
     * @param date The date value to be formatted.
     * @return The HH:mm format string.
     * @author 13120645
     * @since V20141126
     */
    public static String formatHourMinuteFormat(Date date) {
        return format(date, DateFormatConstants.TIME_HOUR_MINUTE_PATTERN);
    }

    /**
     * Parses a time string to produce a Date via the HH:mm pattern.
     *
     * @param strTime The time string to be parsed.
     * @return A Date parsed from the string.
     * @author 13120645
     * @since V20141126
     */
    public static Date parseHourMinute(String strTime) {
        return parse(strTime, DateFormatConstants.TIME_HOUR_MINUTE_PATTERN);
    }

    /**
     * format a time Date to produce a string via the yyyy-MM-dd HH:mm:ss pattern.
     *
     * @return A Date parsed from the string.
     * @author 14091461
     * @since V20150211
     */
    public static String defaultFormatDate(Date date) {
        /*if (defaultPattern == null) {
            defaultPattern = new SimpleDateFormat(DateFormatConstants.DATETIME_PATTERN_STANDARD);
        }*/
        return new SimpleDateFormat(DateFormatConstants.DATETIME_PATTERN_STANDARD).format(date);
    }

    /**
     *
     * 功能描述:根据指定天数获取截止时间 比如传入(2015-02-02 14:11:25,2) 返回:endDate="2015-02-04 00:00:00"
     *
     * @param dateTime 日期参数
     * @param days 天数
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Date getEndDateByDays(Date dateTime, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        Date endDate = calendar.getTime();
        return endDate;
    }

    /**
     *
     */
    static int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    /**
     *
     * 功能描述: <br>
     * 〈校验日期格式是否合法〉
     *
     * @param date
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isValidDate(String date) {
        try {
            int year = Integer.parseInt(date.substring(0, 4));
            if (year <= 0)
                return false;
            int month = Integer.parseInt(date.substring(5, 7));
            if (month <= 0 || month > 12)
                return false;
            int day = Integer.parseInt(date.substring(8, 10));
            if (day <= 0 || day > DAYS[month])
                return false;
            if (month == 2 && day == 29 && !isGregorianLeapYear(year)) {
                return false;
            }
        } catch (NumberFormatException e) {
//            LOGGER.error("numberFormatException",e);
            return false;
        }
        return true;
    }

    public static final boolean isGregorianLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    /**
     * 功能描述: 获取当前时间到下一天零点剩余的秒数<br>
     *
     * @return int秒
     * @since
     */
    public static int getCurrentDaySurplusSecond() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,
                0, 0);
        long timeInMillis = calendar.getTimeInMillis();
        return (int) ((timeInMillis - System.currentTimeMillis()) / 1000);
    }

    /**
     * 功能描述: 获取当前时间到下一月一号零点剩余的秒数<br>
     *
     * @return int秒
     * @since
     */
    public static int getCurrentMonthSurplusSecond() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1, 0, 0, 0);
        long timeInMillis = calendar.getTimeInMillis();
        return (int) ((timeInMillis - System.currentTimeMillis()) / 1000);
    }


    /**
     * 功能描述: 计算当前时间到下一年1月1日0时0分0秒的秒数<br>
     *
     * @return
     * @see
     * @since V2.0.0  (可选)
     */
    public static int getCurrentYearSurplusSecond() {
        Calendar calendar = Calendar.getInstance();
        //时间增加一年
        calendar.add(Calendar.YEAR, 1);
        //时间设置为下一年1月1日0时0分0秒
        calendar.set(calendar.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);
        long timeInMillis = calendar.getTimeInMillis();
        return (int) ((timeInMillis - System.currentTimeMillis()) / 1000);
    }

}