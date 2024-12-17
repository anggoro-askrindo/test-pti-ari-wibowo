package com.example.assesmentbackend.util;

import com.example.assesmentbackend.exception.BadRequestException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateHelperUtil {

    private static final DateTimeFormatter DATE_FORMATTER_DEFAULT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String currentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public static boolean isBetweenDate(String format, String dateStr, Integer min, Integer max) {
        DateTimeFormatter DATE_FORMATTER = format != null ? DateTimeFormatter.ofPattern(format) : DATE_FORMATTER_DEFAULT;
        try {
            LocalDate date = LocalDate.parse(dateStr, DATE_FORMATTER);
            LocalDate today = LocalDate.now();
            LocalDate threeDaysBefore = today.minusDays(min);
            LocalDate threeDaysAfter = today.plusDays(max);
            return !date.isBefore(threeDaysBefore) && !date.isAfter(threeDaysAfter);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isToday(String format, String dateStr) {
        DateTimeFormatter DATE_FORMATTER = format != null ? DateTimeFormatter.ofPattern(format) : DATE_FORMATTER_DEFAULT;
        try {
            LocalDate date = LocalDate.parse(dateStr, DATE_FORMATTER);
            LocalDate today = LocalDate.now();
            return date.equals(today);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isWithinOneYear(String format, String dateFromStr) {
        DateTimeFormatter DATE_FORMATTER = format != null ? DateTimeFormatter.ofPattern(format) : DATE_FORMATTER_DEFAULT;
        try {
            LocalDate dateFrom = LocalDate.parse(dateFromStr, DATE_FORMATTER);
            LocalDate oneYearAfterDateFrom = LocalDate.parse(dateFromStr, DATE_FORMATTER).plusYears(1);
            return !dateFrom.isAfter(oneYearAfterDateFrom);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + dateFromStr);
            return false;
        }
    }

        public static LocalDate toLocalDate (String format, String dateStr){
            DateTimeFormatter DATE_FORMATTER = format != null ? DateTimeFormatter.ofPattern(format) : DATE_FORMATTER_DEFAULT;
            try {
                return LocalDate.parse(dateStr, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new BadRequestException(dateStr+ " could not be parsed at index 2");
            }
        }
    }
