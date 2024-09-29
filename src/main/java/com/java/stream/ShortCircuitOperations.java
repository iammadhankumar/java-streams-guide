package com.java.stream;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 *  Short-circuiting Operations Example
 *  This class demonstrates various short-circuiting operations using Java Streams.
 *  These operations can terminate early without processing the entire stream.
 *  Examples include findFirst, findAny, anyMatch, allMatch, and noneMatch.
 *  
 *  Author: MadhanKumar
 */
@Slf4j
public class ShortCircuitOperations {
	
	 public static void main(String[] args) {
	        // Sample data: A list of integers
	        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

	        /**
	         * findFirst() Operation
	         * This operation returns the first element in the stream.
	         * It short-circuits after finding the first element.
	         */
	        numbers.stream()
	            .findFirst()  // Retrieves the first element from the stream
	            .ifPresent(first -> log.info("First element: " + first));  // Output: First element: 1

	        /**
	         * findAny() Operation
	         * This operation returns any element from the stream (especially useful with parallel streams).
	         * It short-circuits after finding any element.
	         */
	        numbers.parallelStream()
	            .findAny()  // Retrieves any element (random) from the stream
	            .ifPresent(any -> log.info("Any element: " + any));  // Output: Any element: (varies)

	        /**
	         * anyMatch() Operation
	         * This operation checks if any element matches the given predicate.
	         * It short-circuits once a match is found, avoiding further processing.
	         */
	        boolean anyMatch = numbers.stream()
	            .anyMatch(num -> num > 5);  // Checks if any number is greater than 5
	        log.info("Any match > 5: " + anyMatch);  // Output: Any match > 5: true

	        /**
	         * allMatch() Operation
	         * This operation checks if all elements match the given predicate.
	         * It short-circuits if a mismatch is found, terminating early.
	         */
	        boolean allMatch = numbers.stream()
	            .allMatch(num -> num > 0);  // Checks if all numbers are greater than 0
	        log.info("All match > 0: " + allMatch);  // Output: All match > 0: true

	        /**
	         * noneMatch() Operation
	         * This operation checks if no elements match the given predicate.
	         * It short-circuits once a match is found, terminating early.
	         */
	        boolean noneMatch = numbers.stream()
	            .noneMatch(num -> num < 0);  // Checks if no number is less than 0
	        log.info("None match < 0: " + noneMatch);  // Output: None match < 0: true
	    }

}
