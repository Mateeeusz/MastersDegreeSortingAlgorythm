package me.mvti.Algorithms;

import me.mvti.RandomArrayGenerator;

import java.util.Arrays;

public class TimSort {

    private final static int RUN = 32;

    public static void timSort(int[] array) {
        long startTime = System.nanoTime(); // Start pomiaru czasu

        int n = array.length;

        for (int i = 0; i < n; i += RUN) {
            insertionSort(array, i, Math.min((i + RUN - 1), (n - 1)));
        }

        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {

                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min((left + 2 * size - 1), (n - 1));

                if (mid < right) {
                    merge(array, left, mid, right);
                }
            }
        }

        long endTime = System.nanoTime();
        double elapsedTimeInSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.printf("Czas działania algorytmu: %.6f sekund%n", elapsedTimeInSeconds);
    }

    private static void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= left && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int len1 = mid - left + 1;
        int len2 = right - mid;

        int[] leftArray = new int[len1];
        int[] rightArray = new int[len2];

        System.arraycopy(array, left, leftArray, 0, len1);
        System.arraycopy(array, mid + 1, rightArray, 0, len2);

        int i = 0, j = 0, k = left;

        while (i < len1 && j < len2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        while (i < len1) {
            array[k++] = leftArray[i++];
        }

        while (j < len2) {
            array[k++] = rightArray[j++];
        }
    }

    public static void main(String[] args) {
        int[] list = RandomArrayGenerator.generateAndShuffle(1000, 1, 1_000_000, 1);

        System.out.println("Przed sortowaniem: " + Arrays.toString(Arrays.copyOf(list, 20)) + "...");
        timSort(list);
        System.out.println("Po sortowaniu: " + Arrays.toString(Arrays.copyOf(list, 20)) + "...");
    }
}



