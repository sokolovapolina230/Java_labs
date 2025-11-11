package org.example;

import java.io.*;

class FileManager {
    public static String findLineWithMostWords(File file) throws IOException {
        String maxLine = "";
        int maxWords = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                int words = line.trim().isEmpty() ? 0 : line.trim().split("\\s+").length;
                if (words > maxWords) {
                    maxWords = words;
                    maxLine = line;
                }
            }
        }
        return maxLine;
    }

    // Збереження результатів (серіалізація)
    public static void saveResults(String filePath, String maxLine)
            throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(maxLine);
        }
    }
}