package org.example;

import org.junit.jupiter.api.Test;
import java.util.concurrent.ForkJoinPool;
import static org.junit.jupiter.api.Assertions.*;

public class ParallelArraySumTest {

    @Test
    void testSmallArray() {
        int[] arr = {1, 2, 3, 4, 5};
        ForkJoinPool pool = new ForkJoinPool();

        long result = pool.invoke(new ParallelArraySum.SumTask(arr, 0, arr.length));
        assertEquals(15, result);
    }

    @Test
    void testArrayWithThresholdSize() {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) arr[i] = 1;

        ForkJoinPool pool = new ForkJoinPool();
        long result = pool.invoke(new ParallelArraySum.SumTask(arr, 0, arr.length));

        assertEquals(20, result);
    }

    @Test
    void testLargeArray() {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) arr[i] = i % 10;

        ForkJoinPool pool = new ForkJoinPool();

        long expected = 0;
        for (int v : arr) expected += v;

        long result = pool.invoke(new ParallelArraySum.SumTask(arr, 0, arr.length));
        assertEquals(expected, result);
    }

    @Test
    void testRandomArrayComparison() {
        int[] arr = new int[2000];
        java.util.Random rnd = new java.util.Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rnd.nextInt(101);
        }

        long expected = 0;
        for (int v : arr) expected += v;

        ForkJoinPool pool = new ForkJoinPool();
        long result = pool.invoke(new ParallelArraySum.SumTask(arr, 0, arr.length));

        assertEquals(expected, result);
    }

    @Test
    void testArrayNegativeValues() {
        int[] arr = {-5, 10, -4, 7, -2, 11};

        ForkJoinPool pool = new ForkJoinPool();
        long result = pool.invoke(new ParallelArraySum.SumTask(arr, 0, arr.length));

        long expected = -5 + 10 - 4 + 7 - 2 + 11;

        assertEquals(expected, result);
    }
}
