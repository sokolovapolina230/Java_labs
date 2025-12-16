package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class Encryptor {

    private static final Logger logger =
            LogManager.getLogger(Encryptor.class);

    public static void encryptFile(File input, File output, char key)
            throws IOException {

        logger.info("Початок шифрування файлу: {}", input.getName());
        logger.debug("Використовується ключ: {}", key);

        try (FileInputStream fis = new FileInputStream(input);
             FileOutputStream fos = new FileOutputStream(output);
             FilterOutputStream eos =
                     new EncryptFilterOutputStream(fos, key)) {

            byte[] buffer = new byte[1024];
            int read;
            while ((read = fis.read(buffer)) != -1) {
                eos.write(buffer, 0, read);
            }

            logger.info("Шифрування завершено. Результат: {}", output.getName());

        } catch (IOException e) {
            logger.error("Помилка під час шифрування файлу", e);
            throw e;
        }
    }

    public static void decryptFile(File input, File output, char key)
            throws IOException {

        logger.info("Початок дешифрування файлу: {}", input.getName());

        try (FileInputStream fis = new FileInputStream(input);
             FilterInputStream dis =
                     new DecryptFilterInputStream(fis, key);
             FileOutputStream fos = new FileOutputStream(output)) {

            byte[] buffer = new byte[1024];
            int read;
            while ((read = dis.read(buffer)) != -1) {
                fos.write(buffer, 0, read);
            }

            logger.info("Дешифрування завершено. Результат: {}", output.getName());

        } catch (IOException e) {
            logger.error("Помилка під час дешифрування файлу", e);
            throw e;
        }
    }

    // ===== ВНУТРІШНІ ФІЛЬТРИ =====

    private static class EncryptFilterOutputStream extends FilterOutputStream {
        private final char key;

        public EncryptFilterOutputStream(OutputStream out, char key) {
            super(out);
            this.key = key;
        }

        @Override
        public void write(int b) throws IOException {
            super.write((b + key) & 0xFF);
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            for (int i = 0; i < len; i++) {
                write(b[off + i]);
            }
        }
    }

    private static class DecryptFilterInputStream extends FilterInputStream {
        private final char key;

        protected DecryptFilterInputStream(InputStream in, char key) {
            super(in);
            this.key = key;
        }

        @Override
        public int read() throws IOException {
            int b = super.read();
            return (b == -1) ? -1 : (b - key) & 0xFF;
        }
    }
}
