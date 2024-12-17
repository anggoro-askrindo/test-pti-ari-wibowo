package com.example.assesmentbackend.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class JangkaWaktuValidator implements ConstraintValidator<ValidJangkaWaktu, String> {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void initialize(ValidJangkaWaktu validJangkaWaktu) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        try {
            LocalDate date = LocalDate.parse(value, DATE_FORMATTER);
            LocalDate today = LocalDate.now();
            LocalDate threeDaysBefore = today.minusDays(3);
            LocalDate threeDaysAfter = today.plusDays(3);
            return !date.isBefore(threeDaysBefore) && !date.isAfter(threeDaysAfter);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
