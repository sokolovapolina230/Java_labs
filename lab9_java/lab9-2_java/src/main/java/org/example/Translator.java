package org.example;

public class Translator implements Runnable {

    private final CircularBuffer<String> bufferIn;
    private final CircularBuffer<String> bufferOut;
    private final int translatorId;

    public Translator(CircularBuffer<String> bufferIn,
                      CircularBuffer<String> bufferOut,
                      int translatorId) {
        this.bufferIn = bufferIn;
        this.bufferOut = bufferOut;
        this.translatorId = translatorId;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = bufferIn.take();

                String result = "Потік № " + translatorId +
                        " переклав повідомлення  " + msg;
                bufferOut.put(result);

                System.out.println("Translator " + translatorId + ": " + result);

                Thread.sleep(30);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
