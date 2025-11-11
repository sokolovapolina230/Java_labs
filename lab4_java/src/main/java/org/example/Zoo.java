package org.example;

import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private final List<Cage<? extends Animal>> cages = new ArrayList<>();

    public void addCage(Cage<? extends Animal> cage) {
        cages.add(cage);
    }

    public int getCountOfAnimals() {
        int sum = 0;
        for (Cage<? extends Animal> cage : cages) {
            sum += cage.getOccupiedCount();
        }
        return sum;
    }
}
