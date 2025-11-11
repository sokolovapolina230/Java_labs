package org.example;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class JournalTest {

    @Test
    void testAddEntry() {
        CuratorJournal journal = new CuratorJournal();
        JournalEntry entry = new JournalEntry("Соколова", "Поліна",
                LocalDate.of(2006, 1, 12), "+380501111111", "Зелена 20, кв.56");
        journal.addEntry(entry);

        assertEquals(1, journal.getEntries().size());
    }

    @Test
    void testValidName() {
        assertTrue(Validator.isValidName("Степаненко"));
        assertFalse(Validator.isValidName("А122"));
    }

    @Test
    void testValidPhone() {
        assertTrue(Validator.isValidPhone("+380123456789"));
        assertFalse(Validator.isValidPhone("30123455"));
    }

    @Test
    void testBirthDate() {
        assertNotNull(Validator.parseBirthDate("21.01.2000"));
        assertNull(Validator.parseBirthDate("32.99.2000"));
    }
}
