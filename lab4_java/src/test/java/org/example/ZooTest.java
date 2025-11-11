package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.exceptions.AnimalNotFoundException;
import org.example.exceptions.CageFullException;

import static org.junit.jupiter.api.Assertions.*;

public class ZooTest {

    private Zoo zoo;
    private LionCage lionCage;
    private HoofedCage hoofedCage;
    private BirdCage birdCage;

    @BeforeEach
    public void setup() {
        zoo = new Zoo();
        lionCage = new LionCage(4);
        hoofedCage = new HoofedCage(10);
        birdCage = new BirdCage(5);

        zoo.addCage(lionCage);
        zoo.addCage(hoofedCage);
        zoo.addCage(birdCage);
    }

    @Test
    public void testAddAndCountAnimals() throws CageFullException {
        Lion l1 = new Lion("Lion 1");
        Lion l2 = new Lion("Lion 2");
        lionCage.addAnimal(l1);
        lionCage.addAnimal(l2);

        Zebra z = new Zebra("Zebra 1");
        Giraffe g = new Giraffe("Giraffe 1");
        hoofedCage.addAnimal(z);
        hoofedCage.addAnimal(g);

        Eagle e = new Eagle("Eagle 1");
        birdCage.addAnimal(e);

        assertEquals(5, zoo.getCountOfAnimals());
        assertEquals(2, lionCage.getOccupiedCount());
        assertEquals(2, hoofedCage.getOccupiedCount());
        assertEquals(1, birdCage.getOccupiedCount());
    }

    @Test
    public void testCageFullException() throws CageFullException {
        lionCage.addAnimal(new Lion("Lion 1"));
        lionCage.addAnimal(new Lion("Lion 2"));
        lionCage.addAnimal(new Lion("Lion 3"));
        lionCage.addAnimal(new Lion("Lion 4"));
        assertThrows(CageFullException.class, () -> lionCage.addAnimal(new Lion("Lion 5")));
    }

    @Test
    public void testRemoveAnimal() throws CageFullException, AnimalNotFoundException {
        Zebra z = new Zebra("Zebra 1");
        hoofedCage.addAnimal(z);
        assertEquals(1, hoofedCage.getOccupiedCount());
        hoofedCage.removeAnimal(z);
        assertEquals(0, hoofedCage.getOccupiedCount());
        assertThrows(AnimalNotFoundException.class, () -> hoofedCage.removeAnimal(z));
    }
}
