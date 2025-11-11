package org.example;

import java.util.ArrayList;
import java.util.List;

public class CuratorJournal {
    private List<JournalEntry> entries = new ArrayList<>();

    public void addEntry(JournalEntry entry) {
        entries.add(entry);
    }

    public List<JournalEntry> getEntries() {
        return entries;
    }

    public void printAll() {
        if (entries.isEmpty()) {
            System.out.println("Журнал порожній.");
        } else {
            System.out.println("\nВсі записи журналу:");
            entries.forEach(System.out::println);
        }
    }
}

