package me.mvti;

import java.util.Random;

public class RandomArrayGenerator {

    private Random random;

    public RandomArrayGenerator() {
        this.random = new Random();
    }

    public int[] generateAndShuffle(int length, int minValue, int maxValue, int shuffleType) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0.");
        }
        if (minValue >= maxValue) {
            throw new IllegalArgumentException("minValue must be less than maxValue.");
        }

        // Generowanie liczb losowych
        int[] randomArray = new int[length];
        for (int i = 0; i < length; i++) {
            randomArray[i] = random.nextInt(maxValue - minValue + 1) + minValue;
        }

        // Wybór metody mieszania
        if (shuffleType == 1) {
            shuffleUsingJava(randomArray);
        } else if (shuffleType == 2) {
            shuffleCustom(randomArray);
        } else {
            throw new IllegalArgumentException("Invalid shuffleType. Use 1 for Java's shuffle or 2 for custom shuffle.");
        }

        return randomArray;
    }

    private void shuffleUsingJava(int[] array) {
        // Użycie wbudowanego shuffle
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private void shuffleCustom(int[] array) {
        // Własnoręczne mieszanie tablicy (Fisher-Yates Shuffle)
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1); // Losowanie indeksu od 0 do i
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void main(String[] args) {
        RandomArrayGenerator generator = new RandomArrayGenerator();

        // Przykładowe użycie z shuffle Javy
        int[] resultJava = generator.generateAndShuffle(10, 1, 100, 1);
        System.out.println("Shuffle Java:");
        for (int value : resultJava) {
            System.out.print(value + " ");
        }

        // Przykładowe użycie z autorskim shuffle
        int[] resultCustom = generator.generateAndShuffle(10, 1, 100, 2);
        System.out.println("\nShuffle Custom:");
        for (int value : resultCustom) {
            System.out.print(value + " ");
        }
    }
}

