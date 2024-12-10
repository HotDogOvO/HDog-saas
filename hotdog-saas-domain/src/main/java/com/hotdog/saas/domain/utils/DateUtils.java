package com.hotdog.saas.domain.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static LocalDateTime getDateBySecond(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static LocalDateTime getDateByMinutesAgo(LocalDateTime localDateTime, int minutes) {
        return localDateTime.minusMinutes(minutes);
    }

    public static LocalDateTime getTenMinutesAgo(LocalDateTime localDateTime){
        return getDateByMinutesAgo(localDateTime, 10);
    }
}
