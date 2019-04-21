package org.third.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
//  //2018-09-17T09:30:32.026+0000
//    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//    2018-09-17T09:31:30.188Z
//    DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
//  2018-09-17T09:32:42.085
//  DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");    
//    2018-09-17T17:35:54.428CST
//    DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz");
    public static final String UTC_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static ThreadLocal<SimpleDateFormat> UTC_CACHE = new ThreadLocal<SimpleDateFormat>();
    private static ThreadLocal<SimpleDateFormat> DEFAULT_CACHE = new ThreadLocal<SimpleDateFormat>();
public static void main(String[] args) {
	Date today = new Date();
	System.out.println(getUtcSimpleDataFormat().format(today));
}
    private static final SimpleDateFormat getUtcSimpleDataFormat() {
        SimpleDateFormat utcDateFormat = UTC_CACHE.get();
        if (null == utcDateFormat) {
            utcDateFormat = new SimpleDateFormat(UTC_DATE_PATTERN);
//            utcDateFormat.setTimeZone(CommonUtils.UTC_ZONE);
            UTC_CACHE.set(utcDateFormat);
        }
        return utcDateFormat;
    }

    private static final SimpleDateFormat getDefaultSimpleDataFormat() {
        SimpleDateFormat defaultDateFormat = DEFAULT_CACHE.get();
        if (null == defaultDateFormat) {
            defaultDateFormat = new SimpleDateFormat(UTC_DATE_PATTERN);
            DEFAULT_CACHE.set(defaultDateFormat);
        }
        return defaultDateFormat;
    }

    /**
     * Format one Date into date/time string as specified date pattern
     * 
     * @param date time value to be formatted
     * @param pattern specify how date will be formatted
     * @return formatted time string
     */
    public static final String format(Date date, String pattern) {
        return StringUtils.hasText(pattern) ? new SimpleDateFormat(pattern).format(date) : getDefaultSimpleDataFormat().format(date);
    }

    /**
     * Format one Date into date/time string as UTC date pattern without Date locale convertion
     * 
     * @param date time value to be formatted
     * @return formatted time string
     */
    public static final String format(Date date) {
        return getDefaultSimpleDataFormat().format(date);
    }

    /**
     * Return the current time as UTC Time
     * 
     * @return current time
     */
    public static final Date getTimeInUtc() {
        return new Date(getCalendarNowInUtc().getTimeInMillis());
    }

    /**
     * Return the current time as UTC Time
     * 
     * @return current time
     */
    public static final Date getTimeNow() {
        return new Date();
    }

    /**
     * Parse the localTime in UTC format
     * 
     * @param localTime local time
     * @return
     */
    public static final Date parseTime(String localTime) {
        try {
            return getDefaultSimpleDataFormat().parse(localTime);
        } catch (ParseException e) {
            logger.error("Failed to parse date: {}", localTime, e);
            return null;
        }
    }

    /**
     * Parse the UTC Time in string to local time as java {@link java.util.Date}
     * 
     * @param utcTime UTC Time
     * @return
     */
    public static final Date parseUtcTime(String utcTime) {
        try {
            return getUtcSimpleDataFormat().parse(utcTime);
        } catch (ParseException e) {
            logger.error("Failed to parse date: {}", utcTime, e);
            return null;
        }
    }

    /**
     * Return current time as {@link Calendar}
     * 
     * @return
     */
    public static final Calendar getCalendarNowInUtc() {
        Calendar now = Calendar.getInstance();
        int zoneOffset = now.get(java.util.Calendar.ZONE_OFFSET);
        int dstOffset = now.get(java.util.Calendar.DST_OFFSET);
        now.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        return now;
    }

    /**
     * Return current time as {@link Calendar}
     * 
     * @return
     */
    public static final Calendar getCalendarNow() {
        Calendar now = Calendar.getInstance();
        return now;
    }

    /**
     * Returns if time represented by {@link when} is before current time
     * 
     * @param when {@code Object} to compare
     * @return true if time represented by when is before current time. Otherwise, returns false
     * 
     */
    public static boolean isBeforeNow(Date when) {
        return when.getTime() < getTimeNow().getTime();
    }

    /**
     * Returns if time represent by parameter when is after current time
     * 
     * @param when {@code Object} to compare
     * @return true if time represented by when is after current time. Otherwise, returns false
     */
    public static boolean isAfterNow(Date when) {
        return when.getTime() > getTimeNow().getTime();
    }

    /**
     * Get the number of milliseconds by the minutes
     * 
     * @param minute the amount of minutes to convert
     * @return
     */
    public static final long minutes(long minute) {
        return 1000 * 60 * minute;
    }

    /**
     * Get the number of milliseconds by the hours
     * 
     * @param hour the amount of hour to convert
     * @return
     */
    public static final long hours(long hour) {
        return minutes(hour * 60);
    }

    /**
     * Returns a copy of the {@link java.util.Date} plus number of addMinutes
     * 
     * @param src one {@link Date} to be added
     * @param addMinutes the amount of minutes to be added
     * @return new {@link Date} plus addMinutes
     */
    public static Date addMinutes(Date src, int addMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(src);
        calendar.add(Calendar.MINUTE, addMinutes);
        return calendar.getTime();
    }

    /**
     * Returns a copy of the {@link java.util.Date} plus number of addHours
     * 
     * @param src one {@link Date} to be added
     * @param addHours the amount of hours to be added
     * @return new {@link Date} plus addMinutes
     */
    public static Date addHours(Date src, int addHours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(src);
        calendar.add(Calendar.HOUR, addHours);
        return calendar.getTime();
    }
}
