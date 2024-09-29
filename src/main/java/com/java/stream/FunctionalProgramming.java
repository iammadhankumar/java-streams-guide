package com.java.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * FunctionalProgramming demonstrates functional programming techniques using Java Streams.
 * 
 * This class showcases various operations such as filtering, mapping, reducing, and flattening lists,
 * emphasizing declarative code that focuses on what should be done rather than how.
 * Higher-order functions like <code>map()</code>, <code>filter()</code>, and <code>reduce()</code>
 * are utilized to process data immutably, promoting cleaner and more concise code.
 * Streams operate in a lazy and stateless manner, allowing efficient and readable data transformations.
 * 
 * Author: MadhanKumar
 *
 */
@Slf4j
public class FunctionalProgramming {

    /**
     * The main method serves as the entry point for demonstrating various stream operations.
     *
     * <p>This method performs the following operations:</p>
     * <ol>
     *     <li>Filters odd numbers from a list of integers.</li>
     *     <li>Converts a list of strings to uppercase.</li>
     *     <li>Calculates the sum of squares of a list of integers.</li>
     *     <li>Finds the first even number in a list of integers.</li>
     *     <li>Counts the number of words in a list that have more than five characters.</li>
     *     <li>Flattens a list of lists into a single list of integers.</li>
     * </ol>
     *
     * @param args Command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        /* 1. Filter Odd Numbers */
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Filter odd numbers from the list using a stream
        List<Integer> oddNumbers = numbers.stream()
            .filter(n -> n % 2 != 0)  // Keep only numbers that are odd
            .collect(Collectors.toList());  // Collect the results into a new list

        log.info("1. Filter Odd Numbers: {}", oddNumbers);  // Output: [1, 3, 5, 7, 9]
        /* -------------------------------------------------------------------------------------------------------------- */     
        
        /* 2. Convert Strings to Uppercase */
        List<String> names = Arrays.asList("john", "sarah", "alice");

        // Convert each string to uppercase using a stream
        List<String> upperCaseNames = names.stream()
            .map(String::toUpperCase)  // Apply the toUpperCase function to each string
            .collect(Collectors.toList());  // Collect the results into a new list

        log.info("2. Convert Strings to Uppercase: {}", upperCaseNames);  // Output: [JOHN, SARAH, ALICE]
        /* -------------------------------------------------------------------------------------------------------------- */  
        
        /* 3. Sum of Squares of Numbers */
        numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Calculate the sum of squares of the numbers
        int sumOfSquares = numbers.stream()
            .map(n -> n * n)  // Square each number in the list
            .reduce(0, Integer::sum);  // Sum all squares, starting with an initial value of 0

        log.info("3. Sum of Squares of Numbers: {}", sumOfSquares);  // Output: 55
        /* -------------------------------------------------------------------------------------------------------------- */  
        
        /* 4. Find First Even Number */
        numbers = Arrays.asList(1, 3, 5, 7, 8, 10);

        // Find the first even number in the list
        Optional<Integer> firstEven = numbers.stream()
            .filter(n -> n % 2 == 0)  // Filter to keep only even numbers
            .findFirst();  // Get the first element that matches the filter

        // Log the result, ensuring the value is present
        firstEven.ifPresent(value -> log.info("4. Find First Even Number: {}", value)); // Output: 8
        
        /* -------------------------------------------------------------------------------------------------------------- */  
        
        /* 5. Count Words Greater than 5 Characters */
        List<String> words = Arrays.asList("apple", "banana", "strawberry", "kiwi", "mango");

        // Count the number of words with a length greater than 5
        long count = words.stream()
            .filter(word -> word.length() > 5)  // Keep words with more than 5 characters
            .count();  // Count the number of elements that match the filter

        log.info("5. Count Words Greater than 5 Characters: {}", count);  // Output: 2 (banana, strawberry)

        /* -------------------------------------------------------------------------------------------------------------- */  
        
        /* 6. Flatten List of Lists */
        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6)
            );

        // Flatten the list of lists into a single list
        List<Integer> flattenedList = listOfLists.stream()
            .flatMap(List::stream)  // Convert each inner list into a stream and flatten it
            .collect(Collectors.toList());  // Collect the results into a new list

        log.info("6. Flatten List of Lists: {}", flattenedList);  // Output: [1, 2, 3, 4, 5, 6]
    }
}
