package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PerfectNumberFinderTest {

    @Test
    void testKnownPerfectNumbers() {
        PerfectNumberFinder finder = new PerfectNumberFinder();

        assertTrue(finder.isPerfect(6), "6 має бути досконалим");
        assertTrue(finder.isPerfect(28), "28 має бути досконалим");
        assertTrue(finder.isPerfect(496), "496 має бути досконалим");
    }

    @Test
    void testNonPerfectNumbers() {
        PerfectNumberFinder finder = new PerfectNumberFinder();

        assertFalse(finder.isPerfect(1), "1 не є досконалим");
        assertFalse(finder.isPerfect(5), "5 не є досконалим");
        assertFalse(finder.isPerfect(10), "10 не є досконалим");
        assertFalse(finder.isPerfect(100), "100 не є досконалим");
    }

    @Test
    void testNegativeAndZero() {
        PerfectNumberFinder finder = new PerfectNumberFinder();

        assertFalse(finder.isPerfect(0), "0 не є досконалим");
        assertFalse(finder.isPerfect(-6), "-6 не є досконалим");
    }

    @Test
    void testFindPerfectNumbersUpTo30() {
        PerfectNumberFinder finder = new PerfectNumberFinder();
        List<Integer> result = finder.findPerfectNumbers(30);

        assertTrue(result.contains(6));
        assertTrue(result.contains(28));
    }

    @Test
    void testFindPerfectNumbersInRangeWithNone() {
        PerfectNumberFinder finder = new PerfectNumberFinder();
        List<Integer> result = finder.findPerfectNumbers(5);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFindPerfectNumbersWithNegative() {
        PerfectNumberFinder finder = new PerfectNumberFinder();
        List<Integer> result = finder.findPerfectNumbers(-10);

        assertTrue(result.isEmpty(), "Від'ємний діапазон має повертати порожній список");
    }

    @Test
    void testFindPerfectNumbersUpTo500() {
        PerfectNumberFinder finder = new PerfectNumberFinder();
        List<Integer> result = finder.findPerfectNumbers(500);

        assertEquals(6, result.get(0));
        assertEquals(28, result.get(1));
        assertEquals(496, result.get(2));
    }
}