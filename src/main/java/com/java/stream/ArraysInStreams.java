package com.java.stream;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * ArraysInStreamsExample
 * This class demonstrates creating streams from arrays and performing various operations on them,
 * including filtering, mapping, and reducing.
 * 
 * Author: MadhanKumar
 */
@Slf4j
public class ArraysInStreams {

    public static void main(String[] args) {
        // Example 1: Working with an Array of Integers
        int[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        log.info("Even numbers from the integer array:");
        Arrays.stream(intArray)
              .filter(n -> n % 2 == 0) // Filter even numbers
              .forEach(System.out::println); // Print even numbers

        // Example 2: Working with an Array of Strings
        String[] stringArray = {"Apple", "Banana", "Cherry", "Date", "Elderberry"};

        log.info("\nFruits in uppercase:");
        Arrays.stream(stringArray)
              .map(String::toUpperCase) // Convert each fruit to uppercase
              .forEach(System.out::println); // Print the uppercase fruits

        // Example 3: Working with an Array of Doubles
        double[] doubleArray = {1.5, 2.5, 3.5, 4.5, 5.5};

        log.info("\nAverage of the double array:");
        double average = Arrays.stream(doubleArray)
                               .average() // Calculate average
                               .orElse(0.0); // Provide default if empty
        log.info("Average: " + average);

        // Example 4: Working with an Array of Characters
        Character[] charArray = {'a', 'b', 'c', 'd', 'e'};

        log.info("\nCharacters in reverse order:");
        Arrays.stream(charArray) // Convert char array to Character array
              .sorted((c1, c2) -> c2.compareTo(c1)) // Sort in reverse order
              .forEach(System.out::print); // Print sorted characters
    }
}
