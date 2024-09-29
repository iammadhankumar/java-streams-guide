package com.java.stream;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

/**
 * Optional as Result Example
 * This class demonstrates how certain terminal operations return an Optional object to handle empty results gracefully.
 * Examples include: findFirst(), findAny(), min(), max().
 * 
 * Author: MadhanKumar
 */
@Slf4j
public class OptionalAsResult {

    public static void main(String[] args) {

        /**
         * findFirst() Example
         * Retrieves the first element of the stream wrapped in an Optional.
         * If the stream is empty, an empty Optional is returned.
         */
        Stream<String> namesStream = Stream.of("Alice", "Bob", "Charlie");
        Optional<String> firstName = namesStream.findFirst();
        log.info("First Name: " + firstName.orElse("No result found"));  // Output: First Name: Alice

        // Empty stream example for findFirst()
        Stream<String> emptyStream = Stream.empty();
        Optional<String> emptyFirst = emptyStream.findFirst();
        log.info("First Name in Empty Stream: " + emptyFirst.orElse("No result found"));  // Output: First Name in Empty Stream: No result found


        /**
         * findAny() Example
         * Retrieves any element from the stream wrapped in an Optional.
         * Particularly useful in parallel streams as it may return any encountered element.
         */
        Stream<String> parallelNamesStream = Stream.of("Alice", "Bob", "Charlie").parallel();
        Optional<String> anyName = parallelNamesStream.findAny();
        log.info("Any Name: " + anyName.orElse("No result found"));  // Output: Any Name: (randomly any of "Alice", "Bob", or "Charlie")


        /**
         * min() Example
         * Finds the minimum element from the stream based on a comparator and returns it in an Optional.
         */
        IntStream intStream = IntStream.of(9, 5, 3, 7, 1);
        OptionalInt minValue = intStream.min();
        log.info("Minimum Value: " + (minValue.isPresent() ? minValue.getAsInt() : "No result found"));  // Output: Minimum Value: 1

        // Empty stream example for min()
        IntStream emptyIntStream = IntStream.empty();
        OptionalInt emptyMinValue = emptyIntStream.min();
        log.info("Minimum Value in Empty Stream: " + (emptyMinValue.isPresent() ? emptyMinValue.getAsInt() : "No result found"));  // Output: Minimum Value in Empty Stream: No result found


        /**
         * max() Example
         * Finds the maximum element from the stream based on a comparator and returns it in an Optional.
         */
        IntStream maxStream = IntStream.of(9, 5, 3, 7, 1);
        OptionalInt maxValue = maxStream.max();
        log.info("Maximum Value: " + (maxValue.isPresent() ? maxValue.getAsInt() : "No result found"));  // Output: Maximum Value: 9

        // Empty stream example for max()
        IntStream emptyMaxStream = IntStream.empty();
        OptionalInt emptyMaxValue = emptyMaxStream.max();
        log.info("Maximum Value in Empty Stream: " + (emptyMaxValue.isPresent() ? emptyMaxValue.getAsInt() : "No result found"));  // Output: Maximum Value in Empty Stream: No result found
    }
}

