package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        CircularBuffer<String> buffer1 = new CircularBuffer<>(20);
        CircularBuffer<String> buffer2 = new CircularBuffer<>(20);

        // 5 потоків-демонів для генерації
        for (int i = 1; i <= 5; i++) {
            Thread t = new Thread(new Producer(buffer1, i));
            t.setDaemon(true);
            t.start();
        }

        // 2 потоки-демони для перекладу
        for (int i = 1; i <= 2; i++) {
            Thread t = new Thread(new Translator(buffer1, buffer2, i));
            t.setDaemon(true);
            t.start();
        }

        // Основний потік читає 100 повідомлень із другого буфера
        for (int i = 1; i <= 100; i++) {
            String msg = buffer2.take();
            System.out.println("Main: " + msg);
        }

    }
}
