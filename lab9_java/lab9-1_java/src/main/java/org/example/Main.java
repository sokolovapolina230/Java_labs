package org.example;

import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        int accountsCount = 200;
        int threadsCount = 3000;
        Random rand = new Random();

        // Створення рахунків
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < accountsCount; i++) {
            accounts.add(new Account(i, rand.nextInt(10000)));
        }

        // Підрахунок до
        int initialSum = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("Початкова сума: " + initialSum);

        ExecutorService pool = Executors.newFixedThreadPool(50);

        // Запуск паралельних транзакцій
        for (int i = 0; i < threadsCount; i++) {
            pool.submit(() -> {
                Account from = accounts.get(rand.nextInt(accountsCount));
                Account to = accounts.get(rand.nextInt(accountsCount));

                if (from == to) return;

                int amount = rand.nextInt(100);

                synchronized (from) {
                    if (from.getBalance() < amount) return;
                }

                bank.transfer(from, to, amount);
            });
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);

        // Підрахунок після
        int finalSum = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("Кінцева сума: " + finalSum);

        System.out.println(
                initialSum == finalSum
                        ? "Успішно. Гроші не втрачено"
                        : "Помилка. Гроші втрачено"
        );
    }
}
