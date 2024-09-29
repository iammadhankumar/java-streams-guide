package com.java.stream;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

import lombok.extern.slf4j.Slf4j;

/**
 *  Reduction Operations Example
 *  This class demonstrates various reduction operations using Java Streams.
 *  Examples include summing elements, multiplying elements, finding the maximum, counting elements, and calculating the average.
 *  
 *  Author: MadhanKumar
 */
@Slf4j
public class ReductionOperations {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // 1. Sum all elements using reduce
        int sum = numbers.stream()
            .reduce(0, (a, b) -> a + b);  // Initial value 0, then sum elements
        log.info("Sum: " + sum);  // Output: Sum: 15

        // 2. Multiply all elements using reduce
        int product = numbers.stream()
            .reduce(1, (a, b) -> a * b);  // Initial value 1, then multiply elements
        log.info("Product: " + product);  // Output: Product: 120

        // 3. Find the maximum element using reduce
        int max = numbers.stream()
            .reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b);  // Use a lambda to compare
        log.info("Max: " + max);  // Output: Max: 5

        // 4. Count the number of elements
        long count = numbers.stream().count();  // Terminal operation
        log.info("Count: " + count);  // Output: Count: 5

        // 5. Calculate the average of elements
        OptionalDouble average = numbers.stream()
            .mapToInt(Integer::intValue)
            .average();  // Terminal operation for average

        // Check if average is present, then print
        average.ifPresent(avg ->  log.info("Average: " + avg));  // Output: Average: 3.0
    }
}
