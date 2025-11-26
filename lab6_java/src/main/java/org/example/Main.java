package org.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        while (true) {
            System.out.println("\n===== МЕНЮ =====");
            System.out.println("1. Додати елементи вручну");
            System.out.println("2. Додати випадковий масив");
            System.out.println("3. Додати впорядкований масив");
            System.out.println("4. Показати дерево");
            System.out.println("5. Показати всі обходи");
            System.out.println("6. Видалити елемент");
            System.out.println("0. Вийти");
            System.out.print("Ваш вибір: ");

            int choice = readInt(sc);

            switch (choice) {

                case 1 -> {
                    sc.nextLine();
                    System.out.println("Введіть числа через пробіл:");
                    String line = sc.nextLine();
                    String[] nums = line.trim().split("\\s+");

                    for (String s : nums) {
                        try {
                            int val = Integer.parseInt(s);
                            tree.insert(val);
                        } catch (Exception ignored) {}
                    }
                }

                case 2 -> {
                    System.out.print("Кількість елементів: ");
                    int n = readInt(sc);
                    System.out.print("Максимальне значення: ");
                    int max = readInt(sc);

                    Random rnd = new Random();
                    System.out.print("Згенеровано: ");

                    for (int i = 0; i < n; i++) {
                        int val = rnd.nextInt(max) + 1;
                        System.out.print(val + " ");
                        tree.insert(val);
                    }
                    System.out.println();
                }

                case 3 -> {
                    System.out.print("Кількість елементів: ");
                    int n = readInt(sc);
                    System.out.print("Максимальне значення: ");
                    int max = readInt(sc);

                    int[] arr = new int[n];
                    Random rnd = new Random();

                    for (int i = 0; i < n; i++)
                        arr[i] = rnd.nextInt(max) + 1;

                    Arrays.sort(arr);

                    System.out.print("Впорядкований масив: ");
                    for (int x : arr) {
                        System.out.print(x + " ");
                        tree.insert(x);
                    }
                    System.out.println();
                }

                case 4 -> tree.printTree();

                case 5 -> {
                    System.out.print("Inorder:   ");
                    tree.inorder();
                    System.out.print("Preorder:  ");
                    tree.preorder();
                    System.out.print("Postorder: ");
                    tree.postorder();
                }

                case 6 -> {
                    System.out.print("Введіть значення для видалення: ");
                    int val = readInt(sc);
                    boolean removed = tree.delete(val);
                    
                    if (removed)
                        System.out.println("Елемент видалено");
                    else
                        System.out.println("Елемент не знайдено");

                }

                case 0 -> {
                    System.out.println("Кінець!");
                    return;
                }

                default -> System.out.println("Невірний вибір!");
            }
        }
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Введіть число: ");
        }
        return sc.nextInt();
    }
}
