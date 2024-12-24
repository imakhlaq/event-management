package com.eventmanagement.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilsTest {

    @Test
    void getDateTimeInEpochMilli() {
        var time = LocalDateTime.now();
        var epochMilli = DateTimeUtils.getDateTimeInEpochMilli(time);
        var expected = time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        assertEquals(expected, epochMilli);
    }

}