package me.mvti.Algorithms;

import me.mvti.RandomArrayGenerator;

import java.util.Arrays;

public class IntroSort {

    private static final int INSERTION_SORT_THRESHOLD = 16;

    public static void introSort(int[] array) {
        long startTime = System.nanoTime(); // Start pomiaru czasu

        int depthLimit = 2 * (int) (Math.log(array.length) / Math.log(2));
        introSortHelper(array, 0, array.length - 1, depthLimit);

        long endTime = System.nanoTime(); // Koniec pomiaru czasu
        double elapsedTimeInSeconds = (endTime - startTime) / 1_000_000_000.0; // Konwersja na sekundy
        System.out.printf("Czas działania algorytmu: %.6f sekund%n", elapsedTimeInSeconds);
    }

    private static void introSortHelper(int[] array, int left, int right, int depthLimit) {
        int size = right - left + 1;

        if (size < INSERTION_SORT_THRESHOLD) {
            insertionSort(array, left, right);
            return;
        }

        if (depthLimit == 0) {
            heapSort(array, left, right);
            return;
        }

        int pivot = partition(array, left, right);
        introSortHelper(array, left, pivot - 1, depthLimit - 1);
        introSortHelper(array, pivot + 1, right, depthLimit - 1);
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

    private static void heapSort(int[] array, int left, int right) {
        int size = right - left + 1;

        // Build heap
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(array, size, i, left);
        }

        // Extract elements from heap
        for (int i = size - 1; i > 0; i--) {
            swap(array, left, left + i);
            heapify(array, i, 0, left);
        }
    }

    private static void heapify(int[] array, int size, int i, int offset) {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < size && array[offset + leftChild] > array[offset + largest]) {
            largest = leftChild;
        }

        if (rightChild < size && array[offset + rightChild] > array[offset + largest]) {
            largest = rightChild;
        }

        if (largest != i) {
            swap(array, offset + i, offset + largest);
            heapify(array, size, largest, offset);
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
        introSort(list);
        System.out.println("Po sortowaniu: " + Arrays.toString(Arrays.copyOf(list, 20)) + "...");
    }
}

