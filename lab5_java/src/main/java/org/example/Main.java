package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);

        try {
            // Знаходження рядка із максимальною кількістю слів
            System.out.print("Введіть шлях до вхідного файлу: ");
            String filePath = sc.nextLine().trim();
            File inputFile = new File(filePath);

            if (!inputFile.exists()) {
                System.out.println("Файл не знайдено!");
                return;
            }

            String maxLine = FileManager.findLineWithMostWords(inputFile);
            System.out.println("\nРядок із максимальною кількістю слів:");
            System.out.println(maxLine);

            // Шифрування / дешифрування
            System.out.print("\nВведіть ключовий символ для шифрування: ");
            String keyInput = sc.nextLine();
            if (keyInput.isEmpty()) {
                throw new IllegalArgumentException("Ключ не може бути порожнім!");
            }
            char key = keyInput.charAt(0);

            System.out.print("Введіть ім’я файлу для збереження зашифрованих даних: ");
            String encryptedName = sc.nextLine().trim();
            if (encryptedName.isEmpty()) encryptedName = "encrypted.txt";

            File encryptedFile = new File(encryptedName);

            Encryptor.encryptFile(inputFile, encryptedFile, key);
            System.out.println("Файл зашифровано");

            System.out.print("Введіть ім’я файлу для збереження дешифрованих даних: ");
            String decryptedName = sc.nextLine().trim();
            if (decryptedName.isEmpty()) decryptedName = "decrypted.txt";

            File decryptedFile = new File(decryptedName);
            Encryptor.decryptFile(encryptedFile, decryptedFile, key);
            System.out.println("Файл дешифровано");

            // Підрахунок HTML-тегів за URL
            System.out.print("\nВведіть URL для аналізу: ");
            String urlStr = sc.nextLine().trim();
            if (urlStr.isEmpty()) urlStr = "https://example.com";

            Map<String, Integer> tags = TagCounter.countTags(urlStr);

            if (tags.isEmpty()) {
                System.out.println("Теги не знайдені або сторінка пуста.");
            } else {
                System.out.println("\nСортування лексикографічно:");
                tags.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));

                System.out.println("\nСортування за частотою:");
                tags.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));
            }

            // Серіалізація результатів
            System.out.print("\nВведіть шлях для збереження результатів: ");
            String resultsPath = sc.nextLine().trim();
            if (resultsPath.isEmpty()) resultsPath = "results.txt";

            FileManager.saveResults(resultsPath, maxLine);
            System.out.println("Результати збережено у файл: " + resultsPath);

        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
