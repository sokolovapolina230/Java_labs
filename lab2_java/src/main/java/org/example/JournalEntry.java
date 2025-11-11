package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JournalEntry {
    private final String lastName;
    private final String firstName;
    private final LocalDate birthDate;
    private final String phone;
    private final String address;


    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public JournalEntry(String lastName, String firstName, LocalDate birthDate,
                        String phone, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + ", " +
                "Дата народження: " + birthDate.format(FORMATTER) + ", " +
                "Телефон: " + phone + ", " +
                "Адреса: " + address;
    }
}
