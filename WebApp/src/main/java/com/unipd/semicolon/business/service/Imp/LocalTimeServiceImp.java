package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.service.LocalTimeService;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.time.LocalDate;

@Service
public class LocalTimeServiceImp implements LocalTimeService {

    @Override
    public LocalDateTime getLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //correct format of time.
        String formattedDateTime = now.format(formatter);
        return LocalDateTime.parse(formattedDateTime, formatter);
    }

    @Override
    public LocalDate getLocalDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String formattedDate = currentDate.format(formatter);
        return LocalDate.parse(formattedDate, formatter);
    }

    @Override
    public Long nowTime() {
        Date now = new Date();
        return now.getTime();
    }

    @Override
    public LocalDate firstDayOfThisWeek() {
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.ITALY).dayOfWeek();
        System.out.println(now.with(fieldISO, 1)); // 2015-02-09 (Monday)
        return now.with(fieldISO, 1);
    }

    @Override
    public LocalDate firstDayOfThisMonth() {
        LocalDate start = this.getLocalDate().withDayOfMonth(1);
        System.out.println(start);
        return start;
    }

    @Override
    public LocalDate firstDayOfYear() {
        LocalDate start = this.getLocalDate().withDayOfYear(1);
        System.out.println(start);
        return start;
    }

    @Override
    public Map<String, LocalDate> firstAndLastDayOfWeek(Short week) {

        Map<String, LocalDate> firstAndLastDayOfWeek = new HashMap<>();
        LocalDate today = LocalDate.now();
        // Find the start of the nth week ago
        LocalDate startOfNthWeekAgo = today.minusWeeks(week).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        firstAndLastDayOfWeek.put("start", startOfNthWeekAgo);
        // Find the end of the nth week ago
        LocalDate endOfNthWeekAgo = today.minusWeeks(week-1).with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        firstAndLastDayOfWeek.put("end", endOfNthWeekAgo);

        return firstAndLastDayOfWeek;
    }

    @Override
    public Map<String, LocalDate> firstAndLastDayOfMonth(Short month) {

        Map<String, LocalDate> firstAndLastDayOfMonth = new HashMap<>();

        YearMonth currentYearMonth = YearMonth.now();

        // Subtract one month to get the previous month
        YearMonth previousYearMonth = currentYearMonth.minusMonths(month);

        // Get the first day of the previous month
        LocalDate start = previousYearMonth.atDay(1);
        firstAndLastDayOfMonth.put("start", start);

        // Get the last day of the previous month
        LocalDate end = previousYearMonth.atEndOfMonth();
        firstAndLastDayOfMonth.put("end", end);

        return firstAndLastDayOfMonth;
    }
}
