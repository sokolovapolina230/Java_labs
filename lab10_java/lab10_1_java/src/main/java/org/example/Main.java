package org.example;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        String literalString = "Hello world";

        System.out.print("Введіть рядок з клавіатури: ");
        String inputString = scanner.nextLine();

        String programValue = "change";

        System.out.print("Введіть значення для заміни: ");
        String keyboardValue = scanner.nextLine();

        System.out.println("\nДО ЗМІНИ");
        System.out.println("Літерал: " + literalString);
        System.out.println("Ввід:    " + inputString);

        changeStringValue(literalString, programValue);
        changeStringValue(inputString, programValue);

        System.out.println("\nПІСЛЯ ЗМІНИ (програмно)");
        System.out.println("Літерал: " + literalString);
        System.out.println("Ввід:    " + inputString);

        changeStringValue(literalString, keyboardValue);
        changeStringValue(inputString, keyboardValue);

        System.out.println("\nПІСЛЯ ЗМІНИ (з клавіатури)");
        System.out.println("Літерал: " + literalString);
        System.out.println("Ввід:    " + inputString);
    }

    private static void changeStringValue(String str, String newValue) throws Exception {
        Field valueField = String.class.getDeclaredField("value");
        valueField.setAccessible(true);

        valueField.set(str, newValue.getBytes());
    }
}
