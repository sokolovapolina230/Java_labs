package org.example;

import org.example.exceptions.CageFullException;
import org.example.exceptions.AnimalNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class Cage<T extends Animal> {
    private final int capacity;
    private final List<T> occupants = new ArrayList<>();

    public Cage(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Місць має бути більше 0");
        this.capacity = capacity;
    }

    public int getOccupiedCount() {
        return occupants.size();
    }

    public void addAnimal(T animal) throws CageFullException {
        if (occupants.size() >= capacity) {
            throw new CageFullException("Вольєр заповнений (всього місць: " + capacity + ")");
        }
        occupants.add(animal);
    }

    public void removeAnimal(T animal) throws AnimalNotFoundException {
        boolean removed = occupants.remove(animal);
        if (!removed) {
            throw new AnimalNotFoundException("Такої тварини немає у вольєрі: " + animal);
        }
    }

    public List<T> getOccupants() {
        return List.copyOf(occupants);
    }

    @Override
    public String toString() {
        return String.format("%s(capacity=%d, occupied=%d)", getClass().getSimpleName(), capacity, getOccupiedCount());
    }
}