package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PerfectNumberFinder finder = new PerfectNumberFinder();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть максимальне позитивне число для пошуку: ");
        int maxNumber = scanner.nextInt();

        if (maxNumber < 1) {
            System.out.println("Число має бути додатним!");
            scanner.close();
            return;
        }

        List<Integer> result = finder.findPerfectNumbers(maxNumber);

        if (result.isEmpty()) {
            System.out.println("Досконалих чисел не знайдено.");
        } else {
            System.out.println("Досконалі числа: " + result);

            for (int number : result) {
                List<Integer> divisors = finder.getDivisors(number);
                int sum = 0;

                System.out.print("\nЧисло " + number + " є досконалим: ");
                System.out.print(number + " = ");

                // Виводимо формулу
                for (int i = 0; i < divisors.size(); i++) {
                    System.out.print(divisors.get(i));
                    sum += divisors.get(i);

                    if (i < divisors.size() - 1) {
                        System.out.print(" + ");
                    }
                }
                System.out.println(" = " + sum);

            }
        }
        scanner.close();
    }
}