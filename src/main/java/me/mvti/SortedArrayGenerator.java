package me.mvti;

import java.util.Random;

public class SortedArrayGenerator {

    private Random random;

    public SortedArrayGenerator() {
        this.random = new Random();
    }

    public int[] generateSortedArray(int length, int minValue, int maxValue) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0.");
        }
        if (minValue >= maxValue) {
            throw new IllegalArgumentException("minValue must be less than maxValue.");
        }

        int[] sortedArray = new int[length];
        int step = (maxValue - minValue) / Math.max(1, length - 1);

        for (int i = 0; i < length; i++) {
            sortedArray[i] = minValue + i * step;
        }

        return sortedArray;
    }

    public static void main(String[] args) {
        SortedArrayGenerator generator = new SortedArrayGenerator();

        // Przykładowe użycie
        int[] sortedArray = generator.generateSortedArray(20, 1, 100);

        // Wyświetlenie wyniku
        System.out.println("Posortowana tablica:");
        for (int value : sortedArray) {
            System.out.print(value + " ");
        }
    }
}
