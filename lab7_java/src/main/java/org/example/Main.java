package org.example;

import java.util.*;
import java.util.stream.*;

public class Main {

    public static boolean isLatin(String word) {
        return word.matches("[A-Za-z]+");
    }

    public static boolean hasEqualVowelsAndConsonants(String word) {
        String vowels = "aeiouAEIOU";

        long vowelCount = word.chars()
                .mapToObj(c -> (char) c)
                .filter(ch -> vowels.indexOf(ch) >= 0)
                .count();

        long consonantCount = word.length() - vowelCount;

        return vowelCount == consonantCount;
    }

    public static String[] process(String input) {
        return Arrays.stream(input.split("\\s+"))
                .filter(Main::isLatin)
                .filter(Main::hasEqualVowelsAndConsonants)
                .toArray(String[]::new);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВведіть слова через пробіл:");
            String input = scanner.nextLine();

            String[] result = process(input);

            System.out.println("Результат: " + Arrays.toString(result));

            System.out.println("\nБажаєте продовжити? (y/n):");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("n") || choice.equals("no")) {
                System.out.println("Програма завершена.");
                break;
            }
        }
    }
}
