package me.mvti;

import java.util.Random;

public class ReverseSortedArrayGenerator {

    private Random random;

    public ReverseSortedArrayGenerator() {
        this.random = new Random();
    }

    public int[] generateReverseSortedArray(int length, int minValue, int maxValue) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0.");
        }
        if (minValue >= maxValue) {
            throw new IllegalArgumentException("minValue must be less than maxValue.");
        }

        int[] reverseSortedArray = new int[length];
        int step = (maxValue - minValue) / Math.max(1, length - 1);

        for (int i = 0; i < length; i++) {
            reverseSortedArray[i] = maxValue - i * step;
        }

        return reverseSortedArray;
    }

    public static void main(String[] args) {
        ReverseSortedArrayGenerator generator = new ReverseSortedArrayGenerator();

        // Przykładowe użycie
        int[] reverseSortedArray = generator.generateReverseSortedArray(20, 1, 100);

        // Wyświetlenie wyniku
        System.out.println("Odwrotnie posortowana tablica:");
        for (int value : reverseSortedArray) {
            System.out.print(value + " ");
        }
    }
}
