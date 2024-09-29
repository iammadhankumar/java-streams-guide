package com.java.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

/**
 *  Parallel Stream Processing
 *  This class demonstrates various parallel stream processing techniques using Java Streams.
 *  Examples include parallel stream creation, intermediate operations, and terminal operations.
 *  
 *  Author: MadhanKumar
 */
@Slf4j
public class ParallelStream {

    /**
     * The main method serves as the entry point for executing parallel stream examples.
     *
     * @param args Command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        ParallelStream.parallelStreamExamples();
    }

    /**
     * <h3>Parallel Stream Examples</h3>
     * <p>This method demonstrates the use of parallel streams for various operations including mapping, filtering, reducing, and statistics.</p>
     */
    private static void parallelStreamExamples() {
        // Example 1: Creating a Parallel Stream and Mapping
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> squares = numbers.parallelStream()
                .map(n -> n * n)  // Square each number
                .collect(Collectors.toList());
        log.info("Squares: {}", squares);

        // Example 2: Filtering with Parallel Stream
        List<String> fruits = Arrays.asList("Apple", "Banana", "Avocado", "Cherry", "Blueberry");
        log.info("Fruits that start with 'B': {}", 
                fruits.parallelStream()
                .filter(fruit -> fruit.startsWith("B"))  // Filter fruits starting with 'B'
                .collect(Collectors.toList()));

        // Example 3: Reducing with Parallel Stream
        int sum = numbers.parallelStream()
                .reduce(0, Integer::sum);  // Sum all numbers
        log.info("Sum: {}", sum);

        // Example 4: Finding Maximum with Parallel Stream
        Optional<Integer> max = numbers.parallelStream()
                .max(Integer::compareTo);  // Find the maximum number
        max.ifPresent(maxValue -> log.info("Max: {}", maxValue));

        // Example 5: Statistics with Parallel Stream
        IntSummaryStatistics stats = IntStream.rangeClosed(1, 100)
                .parallel()  // Enable parallel processing
                .summaryStatistics();  // Get statistics
        log.info("Statistics - Count: {}, Sum: {}, Min: {}, Max: {}, Average: {}", 
                stats.getCount(), stats.getSum(), stats.getMin(), stats.getMax(), stats.getAverage());

        // Example 6: Sorting with Parallel Stream
        List<String> names = Arrays.asList("John", "Jane", "Alice", "Bob");
        log.info("Sorted names: {}", 
                names.parallelStream()
                .sorted()  // Sort names
                .collect(Collectors.toList()));

        // Example 7: Counting Elements in Parallel
        long count = names.parallelStream().count();  // Count the number of names
        log.info("Count of names: {}", count);
    }
}
