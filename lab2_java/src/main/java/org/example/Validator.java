package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validator {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static boolean isValidName(String name) {
        return name != null && name.matches("[А-Яа-яЇїІіЄєҐґA-Za-z\\-']+");
    }

    public static LocalDate parseBirthDate(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr, FORMATTER);
            if (date.isAfter(LocalDate.now())) return null;
            return date;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\+380\\d{9}");
    }

    public static boolean isValidAddress(String street, String building, String apartment) {
        return !(street.isBlank() || building.isBlank() || apartment.isBlank());
    }
}
