package com.eventmanagement.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeUtils {

    public static long getDateTimeInEpochMilli(LocalDateTime time) {
        var zoneId = ZoneId.systemDefault();
        return time.atZone(zoneId).toInstant().toEpochMilli();
    }
}
