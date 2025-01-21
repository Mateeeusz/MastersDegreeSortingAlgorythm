package me.mvti.Algorithms;

import me.mvti.RandomArrayGenerator;

import java.util.Arrays;

public class InsertionSort {

    public static void insertionSort(int[] array) {
        long startTime = System.nanoTime(); // Start pomiaru czasu

        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;

            // Przesuwanie elementów większych od temp w prawo
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }

            // Wstawienie temp na właściwe miejsce
            array[j + 1] = temp;
        }

        long endTime = System.nanoTime(); // Koniec pomiaru czasu
        double elapsedTimeInSeconds = (endTime - startTime) / 1_000_000_000.0; // Konwersja na sekundy
        System.out.printf("Czas działania algorytmu: %.6f sekund%n", elapsedTimeInSeconds);
    }

    public static void main(String[] args) {
        int[] list = RandomArrayGenerator.generateAndShuffle(100_000, -1_000_000, 1_000_000, 1);

        System.out.println("Przed sortowaniem: " + Arrays.toString(Arrays.copyOf(list, 20)) + "...");
        insertionSort(list);
        System.out.println("Po sortowaniu: " + Arrays.toString(Arrays.copyOf(list, 20)) + "...");
    }
}

