package org.example;

public abstract class Animal {
    private final String name;

    protected Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + name + ")";
    }
}