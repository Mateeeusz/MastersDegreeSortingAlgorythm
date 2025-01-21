package me.mvti.Algorithms;

import me.mvti.RandomArrayGenerator;

import java.util.Arrays;

public class HeapSort {

    public static void heapSort(int[] array) {
        long startTime = System.nanoTime(); // Start pomiaru czasu

        int n = array.length;

        // Budowanie kopca
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Wyciąganie elementów z kopca
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);

            // Ponowne budowanie kopca na zmniejszonym zakresie
            heapify(array, i, 0);
        }

        long endTime = System.nanoTime(); // Koniec pomiaru czasu
        double elapsedTimeInSeconds = (endTime - startTime) / 1_000_000_000.0; // Konwersja na sekundy
        System.out.printf("Czas działania algorytmu: %.6f sekund%n", elapsedTimeInSeconds);
    }

    private static void heapify(int[] array, int size, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        // Sprawdzanie, czy lewe dziecko jest większe od korzenia
        if (left < size && array[left] > array[largest]) {
            largest = left;
        }

        // Sprawdzanie, czy prawe dziecko jest większe od największego
        if (right < size && array[right] > array[largest]) {
            largest = right;
        }

        // Jeśli największy nie jest korzeniem, zamieniamy i rekurencyjnie budujemy kopiec
        if (largest != root) {
            swap(array, root, largest);
            heapify(array, size, largest);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] list = RandomArrayGenerator.generateAndShuffle(1_000_000, -1_000_000, 1_000_000, 1);

        System.out.println("Przed sortowaniem: " + Arrays.toString(Arrays.copyOf(list, 20)) + "...");
        heapSort(list);
        System.out.println("Po sortowaniu: " + Arrays.toString(Arrays.copyOf(list, 20)) + "...");
    }
}

