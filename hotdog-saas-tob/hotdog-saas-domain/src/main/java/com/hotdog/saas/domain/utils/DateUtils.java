package com.hotdog.saas.domain.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYYMMDD = "yyyyMMdd";

    public static final String YYYY_MM_DD_SLASH = "yyyy/MM/dd";

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static LocalDateTime getDateBySecond(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static LocalDateTime getDateByMill(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static LocalDateTime getDateByMinutesAgo(LocalDateTime localDateTime, int minutes) {
        return localDateTime.minusMinutes(minutes);
    }

    public static LocalDateTime getTenMinutesAgo(LocalDateTime localDateTime){
        return getDateByMinutesAgo(localDateTime, 10);
    }

    public static String getFormatDate(String format){
        return getFormatDate(now(), format);
    }

    public static String getFormatDate(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }
}
