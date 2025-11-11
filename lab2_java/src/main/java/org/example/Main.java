package org.example;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CuratorJournal journal = new CuratorJournal();

        while (true) {
            System.out.println("=== Новий запис у журналі ===");

            String lastName;
            do {
                System.out.print("Введіть прізвище: ");
                lastName = scanner.nextLine();
                if (!Validator.isValidName(lastName)) {
                    System.out.println("Помилка: прізвище повинно містити лише літери.");
                }
            } while (!Validator.isValidName(lastName));

            String firstName;
            do {
                System.out.print("Введіть ім'я: ");
                firstName = scanner.nextLine();
                if (!Validator.isValidName(firstName)) {
                    System.out.println("Помилка: ім'я повинно містити лише літери.");
                }
            } while (!Validator.isValidName(firstName));

            LocalDate birthDate;
            do {
                System.out.print("Введіть дату народження (dd.MM.yyyy): ");
                birthDate = Validator.parseBirthDate(scanner.nextLine());
                if (birthDate == null) {
                    System.out.println("Помилка: введіть дату у форматі dd.MM.yyyy і вона не може бути в майбутньому.");
                }
            } while (birthDate == null);

            String phone;
            do {
                System.out.print("Введіть телефон (+380XXXXXXXXX): ");
                phone = scanner.nextLine();
                if (!Validator.isValidPhone(phone)) {
                    System.out.println("Помилка: номер повинен бути у форматі +380XXXXXXXXX.");
                }
            } while (!Validator.isValidPhone(phone));

            String street, building, apartment;
            do {
                System.out.print("Вулиця: ");
                street = scanner.nextLine().trim();

                System.out.print("Будинок: ");
                building = scanner.nextLine().trim();

                System.out.print("Квартира: ");
                apartment = scanner.nextLine().trim();

                if (!Validator.isValidAddress(street, building, apartment)) {
                    System.out.println("Помилка: всі поля адреси необхідно заповнити.");
                }
            } while (!Validator.isValidAddress(street, building, apartment));

            String address = street + " " + building + ", кв. " + apartment;

            JournalEntry entry = new JournalEntry(lastName, firstName, birthDate, phone, address);
            journal.addEntry(entry);

            System.out.println("\nЗапис додано.");

            journal.printAll();

            System.out.print("\nПродовжити? (y/n): ");
            String cont = scanner.nextLine();
            if (cont.equalsIgnoreCase("n")) break;
        }
    }
}

