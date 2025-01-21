package me.mvti.Algorithms;

import me.mvti.RandomArrayGenerator;

import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] array) {
        long startTime = System.nanoTime(); // Start pomiaru czasu

        mergeSortHelper(array, 0, array.length - 1);

        long endTime = System.nanoTime(); // Koniec pomiaru czasu
        double elapsedTimeInSeconds = (endTime - startTime) / 1_000_000_000.0; // Konwersja na sekundy
        System.out.printf("Czas działania algorytmu: %.6f sekund%n", elapsedTimeInSeconds);
    }

    private static void mergeSortHelper(int[] array, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;

            // Sortowanie lewej i prawej połowy
            mergeSortHelper(array, left, middle);
            mergeSortHelper(array, middle + 1, right);

            // Scalanie posortowanych połówek
            merge(array, left, middle, right);
        }
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int sizeLeft = middle - left + 1;
        int sizeRight = right - middle;

        int[] leftArray = new int[sizeLeft];
        int[] rightArray = new int[sizeRight];

        // Kopiowanie danych do tymczasowych tablic
        System.arraycopy(array, left, leftArray, 0, sizeLeft);
        System.arraycopy(array, middle + 1, rightArray, 0, sizeRight);

        int i = 0, j = 0, k = left;

        // Scalanie tymczasowych tablic z powrotem do głównej tablicy
        while (i < sizeLeft && j < sizeRight) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Kopiowanie pozostałych elementów z lewej tablicy (jeśli są)
        while (i < sizeLeft) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        // Kopiowanie pozostałych elementów z prawej tablicy (jeśli są)
        while (j < sizeRight) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] list = RandomArrayGenerator.generateAndShuffle(1_000_000, -1_000_000, 1_000_000, 1);

        System.out.println("Przed sortowaniem: " + Arrays.toString(Arrays.copyOf(list, 20)) + "...");
        mergeSort(list);
        System.out.println("Po sortowaniu: " + Arrays.toString(Arrays.copyOf(list, 20)) + "...");
    }
}
