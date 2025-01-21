package me.mvti;

import me.mvti.Algorithms.IntroSort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import static me.mvti.Algorithms.IntroSort.introSort;
import static me.mvti.Algorithms.TimSort.timSort;

public class RandomArrayGenerator {

    private static Random random;

    public RandomArrayGenerator() {
        this.random = new Random();
    }

    public static int[] generateAndShuffle(int length, int minValue, int maxValue, int shuffleType) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0.");
        }
        if (minValue >= maxValue) {
            throw new IllegalArgumentException("minValue must be less than maxValue.");
        }

        int[] randomArray = new int[length];
        for (int i = 0; i < length; i++) {
            randomArray[i] = random.nextInt(maxValue - minValue + 1) + minValue;
        }

        if (shuffleType == 1) {
            shuffleUsingJava(randomArray);
        } else if (shuffleType == 2) {
            shuffleCustom(randomArray);
        } else {
            throw new IllegalArgumentException("Invalid shuffleType. Use 1 for Java's shuffle or 2 for custom shuffle.");
        }

        return randomArray;
    }

    private static void shuffleUsingJava(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static void shuffleCustom(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void main(String[] args) {
        RandomArrayGenerator generator = new RandomArrayGenerator();

        int[] list = generator.generateAndShuffle(1000000000, -1_000_000, 1_000_000_000, 1);

        try {
            String baseFileName = "IntroSort";
            String beforeFileName = "Before_" + baseFileName + ".txt";
            String afterFileName = "After_" + baseFileName + ".txt";
            String statsFileName = "Statistics_" + baseFileName + ".txt";

            // Zapis tablicy przed sortowaniem
            saveArrayToFile(list, beforeFileName);
            System.out.println("Zapisano plik: " + beforeFileName);

            System.out.println("Przed sortowaniem: " + Arrays.toString(Arrays.copyOf(list, 100)) + "...");

            Runtime runtime = Runtime.getRuntime();
            runtime.gc();
            long startTime = System.nanoTime();
            long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();

            //timSort(list);

            introSort(list);

            long elapsedTime = System.nanoTime() - startTime;
            long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();

            System.out.println("Po sortowaniu: " + Arrays.toString(Arrays.copyOf(list, 100)) + "...");

            saveArrayToFile(list, afterFileName);
            System.out.println("Zapisano plik: " + afterFileName);

            saveStatisticsToFile(statsFileName, elapsedTime, usedMemoryBefore, usedMemoryAfter);
            System.out.println("Zapisano statystyki: " + statsFileName);
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisywania pliku: " + e.getMessage());
        }
    }

    private static void saveArrayToFile(int[] array, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int num : array) {
                writer.write(num + "\n");
            }
        }
    }

    private static void saveStatisticsToFile(String fileName, long elapsedTime, long usedMemoryBefore, long usedMemoryAfter) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("=== Statystyki działania algorytmu ===\n");
            writer.write(String.format("Czas działania: %.6f sekund\n", elapsedTime / 1_000_000_000.0));
            writer.write(String.format("Zużycie pamięci przed sortowaniem: %.2f MB\n", usedMemoryBefore / (1024.0 * 1024.0)));
            writer.write(String.format("Zużycie pamięci po sortowaniu: %.2f MB\n", usedMemoryAfter / (1024.0 * 1024.0)));
            writer.write(String.format("Różnica w zużyciu pamięci: %.2f MB\n", (usedMemoryAfter - usedMemoryBefore) / (1024.0 * 1024.0)));
        }
    }
}

