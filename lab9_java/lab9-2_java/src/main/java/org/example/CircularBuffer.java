package org.example;

public class CircularBuffer<T> {

    private final Object lock = new Object();
    private final T[] buffer;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public CircularBuffer(int capacity) {
        buffer = (T[]) new Object[capacity];
    }

    public void put(T value) throws InterruptedException {
        synchronized (lock) {
            while (size == buffer.length) {
                lock.wait(); // буфер повний – чекаємо
            }

            buffer[tail] = value;
            tail = (tail + 1) % buffer.length;
            size++;

            lock.notifyAll();
        }
    }

    public T take() throws InterruptedException {
        synchronized (lock) {
            while (size == 0) {
                lock.wait(); // буфер порожній – чекаємо
            }

            T value = buffer[head];
            head = (head + 1) % buffer.length;
            size--;

            lock.notifyAll();
            return value;
        }
    }
}
