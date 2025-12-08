package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void testIsLatin() {
        assertTrue(Main.isLatin("Hello"));
        assertTrue(Main.isLatin("abcTYU"));
        assertFalse(Main.isLatin("зоря"));
        assertFalse(Main.isLatin("deal123"));
    }

    @Test
    void testHasEqualVowelsAndConsonants() {
        assertTrue(Main.hasEqualVowelsAndConsonants("aaBB"));
        assertTrue(Main.hasEqualVowelsAndConsonants("Java"));
        assertTrue(Main.hasEqualVowelsAndConsonants("code"));
        assertFalse(Main.hasEqualVowelsAndConsonants("Day"));
        assertFalse(Main.hasEqualVowelsAndConsonants("SSss"));
    }

    @Test
    void testProcess() {
        String input = "hello aaArr test AbaB save";
        String[] result = Main.process(input);

        assertArrayEquals(new String[]{"AbaB", "save"}, result);
    }

    @Test
    void testEmptyInput() {
        String[] result = Main.process("");
        assertEquals(0, result.length);
    }

    @Test
    void testOnlyNonLatinWords() {
        String[] result = Main.process("привіт hello 123");
        assertEquals(0, result.length);
    }

    @Test
    void testMixedWords() {
        String input = "abc aabb aaabbbaa XxYy";
        String[] expected = {"aabb"};

        assertArrayEquals(expected, Main.process(input));
    }
}
