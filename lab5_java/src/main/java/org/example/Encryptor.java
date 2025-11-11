package org.example;

import java.io.*;

class Encryptor {

    public static void encryptFile(File input, File output, char key) throws IOException {
        try (FileInputStream fis = new FileInputStream(input);
             FileOutputStream fos = new FileOutputStream(output);
             FilterOutputStream eos = new EncryptFilterOutputStream(fos, key)) {

            byte[] buffer = new byte[1024];
            int read;
            while ((read = fis.read(buffer)) != -1) {
                eos.write(buffer, 0, read);
            }
        }
    }

    public static void decryptFile(File input, File output, char key) throws IOException {
        try (FileInputStream fis = new FileInputStream(input);
             FilterInputStream dis = new DecryptFilterInputStream(fis, key);
             FileOutputStream fos = new FileOutputStream(output)) {

            byte[] buffer = new byte[1024];
            int read;
            while ((read = dis.read(buffer)) != -1) {
                fos.write(buffer, 0, read);
            }
        }
    }

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
                write(b[off + i] & 0xFF);
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
            if (b == -1) return -1;
            return (b - key) & 0xFF;
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            int i;
            for (i = 0; i < len; i++) {
                int c = read();
                if (c == -1) {
                    return (i == 0) ? -1 : i;
                }
                b[off + i] = (byte) c;
            }
            return i;
        }
    }
}