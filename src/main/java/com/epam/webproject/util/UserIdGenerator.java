package com.epam.webproject.util;

public class UserIdGenerator {
    private static long count;

    private UserIdGenerator() {

    }

    public static long generateId() {
        return ++count;
    }
}
