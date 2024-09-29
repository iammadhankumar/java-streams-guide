package com.java.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

/**
 * Parallel Splitting Example
 * This class demonstrates how to process a large collection in parallel
 * using Java Streams, showing the benefits of splitting the collection into smaller chunks.
 * 
 * Author: MadhanKumar
 */
@Slf4j
public class ParallelSplitting {

    public static void main(String[] args) {
    	
    	/**
    	How Parallel Splitting Works:
    	When parallelStream() is used, Java automatically splits the original collection into smaller chunks. Each chunk can be processed in parallel by different threads.
    	The splitting is done using the ForkJoinPool, which efficiently manages threads and the splitting of tasks, allowing for faster execution of stream operations, especially on larger datasets.
    	*/
    	
        // Create a large list of integers
        // Create a large dataset
        List<Integer> largeDataset = IntStream.rangeClosed(1, 1_000_000)
                .boxed()
                .collect(Collectors.toList());

        // Process the large list in parallel
        List<Integer> processedList = largeDataset.parallelStream()
                .filter(num -> num % 2 == 0)    // Filter even numbers
                .map(num -> num * 2)            // Double each even number
                .collect(Collectors.toList());   // Collect results into a list

        // Display the processed result
        log.info("Processed List: " + processedList);
    }
}