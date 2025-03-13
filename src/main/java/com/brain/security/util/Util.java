package com.brain.security.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Firecode16
 */
public class Util {

    private static final AtomicLong atomic = new AtomicLong(0);

    public static Long generateUID() {
        return atomic.incrementAndGet();
    }

    public static String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(dateTimeFormatter);
    }
}
