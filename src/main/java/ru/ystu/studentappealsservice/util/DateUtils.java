package ru.ystu.studentappealsservice.util;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

public final class DateUtils {

    public static OffsetDateTime now() {
        return OffsetDateTime.now();
    }

    public static OffsetDateTime truncatedNow() {
        return now().truncatedTo(ChronoUnit.SECONDS);
    }
}
