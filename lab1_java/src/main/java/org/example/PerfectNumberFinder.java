package org.example;
import java.util.ArrayList;
import java.util.List;

public class PerfectNumberFinder {

    /**
     * Перевіряє, чи є число досконалим
     * @param number число для перевірки
     * @return true якщо число досконале, false якщо ні
     */
    public boolean isPerfect(int number) {
        if (number <= 1) {
            return false;
        }

        int sum = 0;

        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }

        return sum == number;
    }

    /**
     * Знаходить всі досконалі числа від 1 до maxNumber
     * @param maxNumber верхня межа діапазону
     * @return список досконалих чисел
     */
    public List<Integer> findPerfectNumbers(int maxNumber) {
        List<Integer> perfectNumbers = new ArrayList<>();

        for (int i = 1; i <= maxNumber; i++) {
            if (isPerfect(i)) {
                perfectNumbers.add(i);
            }
        }

        return perfectNumbers;
    }

    /**
     * Знаходить всі дільники числа (крім самого числа)
     * @param number число для якого шукаємо дільники
     * @return список дільників
     */
    public List<Integer> getDivisors(int number) {
        List<Integer> divisors = new ArrayList<>();

        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                divisors.add(i);
            }
        }

        return divisors;
    }
}