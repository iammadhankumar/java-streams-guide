package com.java.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Performance Optimizations Example
 * This class demonstrates how to optimize stream operations by combining filter and map into a single pass.
 * Efficient data processing is achieved by pipelining multiple operations without creating intermediate collections.
 * 
 * Author: MadhanKumar
 */
public class PerformanceOptimizations {

    public static void main(String[] args) {
        // Create a large dataset of integers from 1 to 1,000,000
        List<Integer> largeDataset = IntStream.rangeClosed(1, 1_000_000)
                .boxed()
                .collect(Collectors.toList());

        // Optimized Stream Processing: Combining filter and map in a single pass
        long startTime = System.currentTimeMillis();

        List<Integer> optimizedResult = largeDataset.stream()
                .filter(num -> num % 2 == 0) // Filter even numbers
                .map(num -> num * 2)         // Double each number
                .collect(Collectors.toList()); // Collect the results

        long endTime = System.currentTimeMillis();

        // Display results
        System.out.println("Optimized Result Size: " + optimizedResult.size());
        System.out.println("Optimized Processing Time: " + (endTime - startTime) + " ms");

        // Comparison with non-optimized processing
        long nonOptimizedStartTime = System.currentTimeMillis();

        List<Integer> nonOptimizedResult = largeDataset.stream()
                .filter(num -> num % 2 == 0) // Filter even numbers
                .collect(Collectors.toList()); // Collect intermediate result

        // Intermediate list created, now mapping
        List<Integer> mappedResult = nonOptimizedResult.stream()
                .map(num -> num * 2)         // Double each number
                .collect(Collectors.toList()); // Collect the final result

        long nonOptimizedEndTime = System.currentTimeMillis();

        // Display non-optimized results
        System.out.println("Non-Optimized Result Size: " + mappedResult.size());
        System.out.println("Non-Optimized Processing Time: " + (nonOptimizedEndTime - nonOptimizedStartTime) + " ms");
    }
}

