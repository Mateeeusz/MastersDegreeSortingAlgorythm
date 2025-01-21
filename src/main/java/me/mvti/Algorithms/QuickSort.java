package me.mvti.Algorithms;

import me.mvti.RandomArrayGenerator;

import java.util.Arrays;

public class QuickSort {

    public static void quickSort(int[] array) {
        long startTime = System.nanoTime();

        quickSortRecursive(array, 0, array.length - 1);

        long endTime = System.nanoTime();
        double elapsedTimeInSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.printf("Czas działania algorytmu: %.6f sekund%n", elapsedTimeInSeconds);
    }

    private static void quickSortRecursive(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);

            quickSortRecursive(array, low, pivotIndex - 1);
            quickSortRecursive(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        int[] list = RandomArrayGenerator.generateAndShuffle(1000, 1, 1_000_000, 1);

        System.out.println("Przed sortowaniem: " + Arrays.toString(Arrays.copyOf(list, 20)) + "...");
        quickSort(list);
        System.out.println("Po sortowaniu: " + Arrays.toString(Arrays.copyOf(list, 20)) + "...");
    }
}

