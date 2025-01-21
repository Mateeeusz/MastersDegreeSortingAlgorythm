package me.mvti.Algorithms;

import me.mvti.RandomArrayGenerator;

import java.util.Arrays;

public class HybridSort {

    private static final int INSERTION_SORT_THRESHOLD = 32;

    public static void hybridSort(int[] array) {
        long startTime = System.nanoTime(); // Start pomiaru czasu

        hybridSortHelper(array, 0, array.length - 1);

        long endTime = System.nanoTime(); // Koniec pomiaru czasu
        double elapsedTimeInSeconds = (endTime - startTime) / 1_000_000_000.0; // Konwersja na sekundy
        System.out.printf("Czas działania algorytmu: %.6f sekund%n", elapsedTimeInSeconds);
    }

    private static void hybridSortHelper(int[] array, int left, int right) {
        if (right - left + 1 < INSERTION_SORT_THRESHOLD) {
            insertionSort(array, left, right);
            return;
        }

        int pivot = partition(array, left, right);
        hybridSortHelper(array, left, pivot - 1);
        hybridSortHelper(array, pivot + 1, right);
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

    private static int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] list = RandomArrayGenerator.generateAndShuffle(1_000_000, -1_000_000, 1_000_000, 1);

        System.out.println("Przed sortowaniem: " + Arrays.toString(Arrays.copyOf(list, 20)) + "...");
        hybridSort(list);
        System.out.println("Po sortowaniu: " + Arrays.toString(Arrays.copyOf(list, 20)) + "...");
    }
}
