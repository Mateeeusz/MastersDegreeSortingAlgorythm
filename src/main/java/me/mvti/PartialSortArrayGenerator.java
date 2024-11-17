package me.mvti;

import java.util.Random;
import java.util.Arrays;

public class PartialSortArrayGenerator {

    private Random random;

    public PartialSortArrayGenerator() {
        this.random = new Random();
    }

    public int[] generatePartiallySortedArray(int length, int minValue, int maxValue, double sortPercentage) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0.");
        }
        if (minValue >= maxValue) {
            throw new IllegalArgumentException("minValue must be less than maxValue.");
        }
        if (sortPercentage < 0.0 || sortPercentage > 1.0) {
            throw new IllegalArgumentException("sortPercentage must be between 0.0 and 1.0.");
        }

        // Generowanie liczb losowych
        int[] randomArray = new int[length];
        for (int i = 0; i < length; i++) {
            randomArray[i] = random.nextInt(maxValue - minValue + 1) + minValue;
        }

        // Obliczanie liczby elementów do posortowania
        int elementsToSort = (int) (length * sortPercentage);

        // Sortowanie wymaganej części tablicy
        Arrays.sort(randomArray, 0, elementsToSort);

        return randomArray;
    }

    public static void main(String[] args) {
        PartialSortArrayGenerator generator = new PartialSortArrayGenerator();

        // Przykładowe użycie
        int[] partiallySortedArray = generator.generatePartiallySortedArray(20, 1, 100, 0.5);

        // Wyświetlenie wyniku
        System.out.println("Częściowo posortowana tablica:");
        for (int value : partiallySortedArray) {
            System.out.print(value + " ");
        }
    }
}
