package com.unipd.semicolon.business.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public interface LocalTimeService {

    LocalDateTime getLocalDateTime();

    LocalDate getLocalDate();

    Long nowTime();

    LocalDate firstDayOfThisWeek();

    LocalDate firstDayOfThisMonth();

    LocalDate firstDayOfYear();

    Map<String, LocalDate> firstAndLastDayOfWeek(Short month);

    Map<String, LocalDate> firstAndLastDayOfMonth(Short month);
}
