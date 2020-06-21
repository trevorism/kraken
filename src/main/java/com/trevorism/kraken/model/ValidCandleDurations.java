package com.trevorism.kraken.model;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public final class ValidCandleDurations {

    public static final Duration MINUTE = Duration.ofMinutes(1);
    public static final Duration FIVE_MINUTES = Duration.ofMinutes(5);
    public static final Duration FIFTEEN_MINUTES = Duration.ofMinutes(15);
    public static final Duration THIRTY_MINUTES = Duration.ofMinutes(30);
    public static final Duration HOUR = Duration.ofHours(1);
    public static final Duration SIX_HOURS = Duration.ofHours(6);
    public static final Duration DAY = Duration.ofDays(1);
    public static final Duration WEEK = Duration.ofDays(7);
    public static final Duration FIFTEEN_DAYS = Duration.ofDays(15);

    public static final List<Duration> validDurations = Arrays.asList(MINUTE, FIVE_MINUTES,
            FIFTEEN_MINUTES, THIRTY_MINUTES, HOUR, SIX_HOURS, DAY, WEEK, FIFTEEN_DAYS);

    public static boolean validate(Duration duration){
        return validDurations.contains(duration);
    }
}
