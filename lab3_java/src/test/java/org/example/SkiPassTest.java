package org.example;

import org.junit.jupiter.api.Test;
import org.example.model.*;
import org.example.controller.SkiPassSystem;

import static org.junit.jupiter.api.Assertions.*;

public class SkiPassTest {

    @Test
    public void testAccessAndBlock() {
        SkiPassSystem system = new SkiPassSystem();
        LiftSkiPass pass = new LiftSkiPass(SkiPassType.WEEKDAY, 5);
        system.addSkiPass(pass);

        assertTrue(system.checkAccess(pass.getId()));
        system.blockSkiPass(pass.getId());
        assertFalse(system.checkAccess(pass.getId()));
    }
}
