package org.example;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelArraySum {

    private static final int THRESHOLD = 20;

    public static void main(String[] args) {

        int[] array = new int[1_000_000];
        Random rnd = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = rnd.nextInt(101);  // від 0 до 100
        }

        ForkJoinPool pool = new ForkJoinPool();

        long start = System.currentTimeMillis();
        Long result = pool.invoke(new SumTask(array, 0, array.length));
        long end = System.currentTimeMillis();

        System.out.println("Сума елементів масиву: " + result);
        System.out.println("Час виконання: " + (end - start) + " мс");
    }

    static class SumTask extends RecursiveTask<Long> {

        private final int[] arr;
        private final int start;
        private final int end;

        public SumTask(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {

            int length = end - start;

            // Якщо елементів мало, рахуємо послідовно
            if (length <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += arr[i];
                }
                return sum;
            }

            // Інакше ділимо на два підзавдання
            int mid = start + length / 2;

            SumTask left = new SumTask(arr, start, mid);
            SumTask right = new SumTask(arr, mid, end);

            left.fork();
            long rightResult = right.compute();
            long leftResult = left.join();

            return leftResult + rightResult;
        }
    }
}
