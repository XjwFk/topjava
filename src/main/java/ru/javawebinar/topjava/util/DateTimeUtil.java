package ru.javawebinar.topjava.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final LocalDate DATE_MIN = LocalDate.of(1, 1, 1);
    ;
    public static final LocalDate DATE_MAX = LocalDate.of(3000, 1, 1);

    public static <T extends Comparable<? super T>> boolean isBetween(T lt, T startTime, T endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static LocalDate parseLocalDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return StringUtils.isEmpty(string) ? null : LocalDate.parse(string, formatter);
    }

    public static LocalTime parseLocalTime(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return StringUtils.isEmpty(string) ? null : LocalTime.parse(string, formatter);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}
