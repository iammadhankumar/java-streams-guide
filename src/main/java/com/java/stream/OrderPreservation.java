package com.java.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * Order Preservation Example
 * This class demonstrates how streams from ordered sources retain the order of elements
 * unless explicitly modified by operations like unordered().
 * 
 * Author: MadhanKumar
 */
@Slf4j
public class OrderPreservation {

    public static void main(String[] args) {
        // Create an ordered list of integers
        List<Integer> orderedList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        /**
         When the order of elements is important for the application's logic, or when processing the data in the same order
         is necessary for clarity and consistency. Examples include time series data, user activity logs,
         or any scenario where sequence matters.
         */
        // Process the stream while preserving order
        List<Integer> preservedOrderResult = orderedList.stream()
                .filter(num -> num % 2 == 0) // Filter even numbers
                .map(num -> num * 2)         // Double each even number
                .collect(Collectors.toList()); // Collect the results

        // Display the result
        log.info("Preserved Order Result: " + preservedOrderResult);

        /**
        When performance is a priority, especially with large datasets, and the order of elements does not affect 
        the correctness of the operations being performed. This is often the case for aggregations or calculations where 
        the result does not depend on the order.
        */
        // Modify the stream to demonstrate unordered processing
        List<Integer> unorderedResult = orderedList.stream()
                .unordered() // Explicitly specify unordered processing
                .filter(num -> num % 2 == 0) // Filter even numbers
                .map(num -> num * 2)         // Double each even number
                .collect(Collectors.toList()); // Collect the results

        // Display the unordered result
        log.info("Unordered Result: " + unorderedResult);
    }
}

