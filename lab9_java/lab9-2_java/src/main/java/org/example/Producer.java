package org.example;

public class Producer implements Runnable {

    private final CircularBuffer<String> buffer;
    private final int producerId;

    public Producer(CircularBuffer<String> buffer, int producerId) {
        this.buffer = buffer;
        this.producerId = producerId;
    }

    @Override
    public void run() {
        try {
            int counter = 1;
            while (true) {
                String msg = "Потік № " + producerId +
                        " згенерував повідомлення " + counter++;
                buffer.put(msg);

                System.out.println("Producer " + producerId + ": " + msg);

                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
