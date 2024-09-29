package com.java.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

/**
 * Built-in Parallelism Control Example
 * This class demonstrates how to switch between parallel and sequential stream processing modes.
 * It includes examples of processing a large dataset in both modes and comparing the results.
 * 
 * Author: MadhanKumar
 */
@Slf4j
public class ParallelismControl {

    public static void main(String[] args) {
        // Create a large dataset
        List<Integer> largeDataset = IntStream.rangeClosed(1, 1_000_000)
                .boxed()
                .collect(Collectors.toList());

        // Sequential Processing
        long sequentialStartTime = System.currentTimeMillis();
        List<Integer> sequentialResult = largeDataset.stream()
                .filter(num -> num % 2 == 0) // Filtering even numbers
                .map(num -> num * 2)         // Doubling each number
                .collect(Collectors.toList());
        long sequentialEndTime = System.currentTimeMillis();

        // Display results
        log.info("Sequential Processing Time: " + (sequentialEndTime - sequentialStartTime) + " ms");
        log.info("Sequential Result Size: " + sequentialResult.size());

        // Parallel Processing
        long parallelStartTime = System.currentTimeMillis();
        List<Integer> parallelResult = largeDataset.parallelStream()
                .filter(num -> num % 2 == 0) // Filtering even numbers
                .map(num -> num * 2)         // Doubling each number
                .collect(Collectors.toList());
        long parallelEndTime = System.currentTimeMillis();

        // Display results
        log.info("Parallel Processing Time: " + (parallelEndTime - parallelStartTime) + " ms");
        log.info("Parallel Result Size: " + parallelResult.size());

        // Switching back to sequential processing
        List<Integer> backToSequential = parallelResult.stream()
                .sequential()
                .collect(Collectors.toList());

        log.info("Result after switching back to Sequential Size: " + backToSequential.size());
    }
}
