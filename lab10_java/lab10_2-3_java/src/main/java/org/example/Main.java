package org.example;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);

        // Вибір мови
        Locale locale = selectLanguage(sc);
        ResourceBundle bundle =
                ResourceBundle.getBundle("location.messages", locale);

        try {
            System.out.print(bundle.getString("prompt.file"));
            String filePath = sc.nextLine().trim();
            File inputFile = new File(filePath);

            if (!inputFile.exists()) {
                System.out.println(bundle.getString("error.filenotfound"));
                return;
            }

            System.out.print(bundle.getString("prompt.key"));
            String keyInput = sc.nextLine();
            if (keyInput.isEmpty()) {
                System.out.println(bundle.getString("error.keyempty"));
                return;
            }

            char key = keyInput.charAt(0);

            File encrypted = new File("encrypted.txt");
            File decrypted = new File("decrypted.txt");

            Encryptor.encryptFile(inputFile, encrypted, key);
            Encryptor.decryptFile(encrypted, decrypted, key);

            System.out.println(bundle.getString("done"));

        } catch (Exception e) {
            System.out.println(bundle.getString("error.general") + e.getMessage());
        }
    }

    private static Locale selectLanguage(Scanner sc) {
        System.out.println("1 - Українська");
        System.out.println("2 - English");
        System.out.print("Оберіть мову / Select language: ");

        String choice = sc.nextLine();
        if ("2".equals(choice)) {
            return Locale.ENGLISH;
        }
        return new Locale("uk", "UA");
    }
}
