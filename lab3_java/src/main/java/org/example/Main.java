package org.example;

import org.example.controller.SkiPassSystem;
import org.example.model.LiftSkiPass;
import org.example.model.SkiPass;
import org.example.model.SkiPassType;
import org.example.model.TimeSkiPass;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SkiPassSystem system = new SkiPassSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n---- Турнікет лижного підйомника ----");
            System.out.println("1. Випустити ski-pass");
            System.out.println("2. Перевірити доступ");
            System.out.println("3. Показати статистику");
            System.out.println("4. Заблокувати ski-pass");
            System.out.println("5. Вийти");
            System.out.print("Вибір дії: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            switch (choice) {
                case 1 -> issueSkiPass(system, scanner);
                case 2 -> checkAccess(system, scanner);
                case 3 -> system.printStatistics();
                case 4 -> blockSkiPass(system, scanner);
                case 5 -> {
                    System.out.println("До побачення!");
                    return;
                }
                default -> System.out.println("Невірний вибір!");
            }
        }
    }

    private static void issueSkiPass(SkiPassSystem system, Scanner scanner) {
        System.out.println("\nОберіть тип ski-pass:");
        System.out.println("1. На робочі дні");
        System.out.println("2. На вихідні дні");
        System.out.println("3. Абонемент на сезон");
        System.out.print("Ваш вибір: ");
        int typeChoice = scanner.nextInt();

        SkiPassType type = switch (typeChoice) {
            case 1 -> SkiPassType.WEEKDAY;
            case 2 -> SkiPassType.WEEKEND;
            case 3 -> SkiPassType.SEASON;
            default -> SkiPassType.WEEKDAY;
        };

        if (type == SkiPassType.SEASON) {
            SkiPass seasonPass = new TimeSkiPass(type, 24 * 90); // наприклад, 90 днів
            system.addSkiPass(seasonPass);
            System.out.println("Створено абонемент на сезон! ID: " + seasonPass.getId());
            return;
        }

        System.out.println("\nОберіть варіант:");
        System.out.println("1. Без обліку кількості підйомів");
        System.out.println("2. По кількості підйомів");
        System.out.print("Ваш вибір: ");
        int option = scanner.nextInt();

        if (option == 1) {
            System.out.println("\nОберіть тривалість:");
            System.out.println("1. Півдня (9–13)");
            System.out.println("2. Півдня (13–17)");
            System.out.println("3. 1 день");
            System.out.println("4. 2 дні");
            System.out.print("Ваш вибір: ");
            int timeChoice = scanner.nextInt();

            int hours = switch (timeChoice) {
                case 1, 2 -> 4;
                case 3 -> 8;
                case 4 -> 16;
                default -> 4;
            };

            SkiPass pass = new TimeSkiPass(type, hours);
            system.addSkiPass(pass);
            System.out.println("Створено ski-pass без обліку підйомів. ID: " + pass.getId());
        } else {
            System.out.println("\nОберіть кількість підйомів:");
            System.out.println("1. 10");
            System.out.println("2. 20");
            System.out.println("3. 50");
            System.out.println("4. 100");
            System.out.print("Ваш вибір: ");
            int liftChoice = scanner.nextInt();

            int lifts = switch (liftChoice) {
                case 1 -> 10;
                case 2 -> 20;
                case 3 -> 50;
                case 4 -> 100;
                default -> 10;
            };

            SkiPass pass = new LiftSkiPass(type, lifts);
            system.addSkiPass(pass);
            System.out.println("Створено ski-pass на " + lifts + " підйомів. ID: " + pass.getId());
        }
    }

    private static void checkAccess(SkiPassSystem system, Scanner scanner) {
        System.out.print("Введіть ID ski-pass: ");
        int id = scanner.nextInt();
        boolean access = system.checkAccess(id);
        System.out.println(access ? "Прохід дозволено" : "Прохід заборонено");
    }

    private static void blockSkiPass(SkiPassSystem system, Scanner scanner) {
        System.out.print("Введіть ID ski-pass для блокування: ");
        int id = scanner.nextInt();
        system.blockSkiPass(id);
        System.out.println("Ski-pass " + id + " заблоковано.");
    }
}
