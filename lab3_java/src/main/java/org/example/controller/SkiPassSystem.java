package org.example.controller;

import org.example.model.*;
import java.util.*;
import java.util.stream.Collectors;

public class SkiPassSystem {
    private final Map<Integer, SkiPass> passes = new HashMap<>();

    public void addSkiPass(SkiPass pass) {
        passes.put(pass.getId(), pass);
    }

    public boolean checkAccess(int id) {
        SkiPass pass = passes.get(id);
        return pass != null && pass.use();
    }

    public void blockSkiPass(int id) {
        SkiPass pass = passes.get(id);
        if (pass != null) pass.block();
    }

    public void printStatistics() {
        System.out.println("\n Статистика SkiPass (всі картки)");

        int total = passes.size();
        long allowedCount = passes.values().stream().filter(pass -> !pass.isBlocked()).count();
        long blockedCount = total - allowedCount;

        System.out.println("Сумарно: всього=" + total + ", дозволено=" + allowedCount + ", заблоковано=" + blockedCount);

        System.out.println("\n По типах ski-pass");
        Map<SkiPassType, Long> countByType = passes.values().stream()
                .collect(Collectors.groupingBy(SkiPass::getType, Collectors.counting()));
        for (SkiPassType type : SkiPassType.values()) {
            long count = countByType.getOrDefault(type, 0L);
            System.out.println(type + ": " + count + " карток");
        }

        System.out.println("\n Деталі кожної картки");
        for (SkiPass pass : passes.values()) {
            System.out.print("ID: " + pass.getId() + ", type: " + pass.getType() + ", blocked: " + pass.isBlocked());

            if (pass instanceof LiftSkiPass liftPass) {
                System.out.print(", remaining lifts: " + liftPass.getRemainingLifts());
            } else if (pass instanceof TimeSkiPass timePass) {
                System.out.print(", valid for: " + timePass.getValidHours() + " годин");
            } else if (pass.getType() == SkiPassType.SEASON) {
                System.out.print(", season pass");
            }

            System.out.println();
        }
    }
}
