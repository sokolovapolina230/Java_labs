package org.example;

public class Account {
    private final int id;
    private int balance;

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized void withdraw(int amount) {
        if (balance < amount)
            throw new IllegalArgumentException("Грошей недостатньо");
        balance -= amount;
    }

    public synchronized int getBalance() {
        return balance;
    }
}
