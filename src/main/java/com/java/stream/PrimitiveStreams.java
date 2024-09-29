package com.java.stream;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import lombok.extern.slf4j.Slf4j;

/**
 *  Primitive Streams Example
 *  This class demonstrates the use of special stream types for primitive data types to avoid boxing overhead:
 *  - IntStream for int
 *  - LongStream for long
 *  - DoubleStream for double
 *  It also demonstrates operations specific to numeric streams such as sum(), average(), min(), and max().
 *  
 * Author: MadhanKumar
 */
@Slf4j
public class PrimitiveStreams {

    public static void main(String[] args) {

        /**
         * IntStream Example
         * This stream processes primitive int values.
         * It demonstrates the sum, average, min, and max operations.
         */
        IntStream intStream = IntStream.range(1, 10);  // Generates an IntStream with values 1 to 9

        int sum = intStream.sum();  // Sum of all elements
        log.info("IntStream Sum: " + sum);  // Output: IntStream Sum: 45

        // Re-create the stream as the previous one was consumed
        OptionalDouble average = IntStream.range(1, 10).average();  // Average of all elements
        log.info("IntStream Average: " + (average.isPresent() ? average.getAsDouble() : "N/A"));  // Output: IntStream Average: 5.0

        OptionalInt min = IntStream.range(1, 10).min();  // Minimum element
        log.info("IntStream Min: " + (min.isPresent() ? min.getAsInt() : "N/A"));  // Output: IntStream Min: 1

        OptionalInt max = IntStream.range(1, 10).max();  // Maximum element
        log.info("IntStream Max: " + (max.isPresent() ? max.getAsInt() : "N/A"));  // Output: IntStream Max: 9

        /**
         * LongStream Example
         * This stream processes primitive long values.
         * It demonstrates the sum, average, min, and max operations.
         */
        LongStream longStream = LongStream.range(1L, 100L);  // Generates a LongStream with values 1 to 99

        long longSum = longStream.sum();  // Sum of all elements
        log.info("LongStream Sum: " + longSum);  // Output: LongStream Sum: 4950

        // Re-create the stream as the previous one was consumed
        OptionalDouble longAverage = LongStream.range(1L, 100L).average();  // Average of all elements
        log.info("LongStream Average: " + (longAverage.isPresent() ? longAverage.getAsDouble() : "N/A"));  // Output: LongStream Average: 50.0

        long longMin = LongStream.range(1L, 100L).min().orElseThrow();  // Minimum element
        log.info("LongStream Min: " + longMin);  // Output: LongStream Min: 1

        long longMax = LongStream.range(1L, 100L).max().orElseThrow();  // Maximum element
        log.info("LongStream Max: " + longMax);  // Output: LongStream Max: 99

        /**
         * DoubleStream Example
         * This stream processes primitive double values.
         * It demonstrates the sum, average, min, and max operations.
         */
        DoubleStream doubleStream = DoubleStream.of(1.5, 2.5, 3.5, 4.5, 5.5);  // Generates a DoubleStream with given values

        double doubleSum = doubleStream.sum();  // Sum of all elements
        log.info("DoubleStream Sum: " + doubleSum);  // Output: DoubleStream Sum: 17.5

        // Re-create the stream as the previous one was consumed
        OptionalDouble doubleAverage = DoubleStream.of(1.5, 2.5, 3.5, 4.5, 5.5).average();  // Average of all elements
        log.info("DoubleStream Average: " + (doubleAverage.isPresent() ? doubleAverage.getAsDouble() : "N/A"));  // Output: DoubleStream Average: 3.5

        OptionalDouble doubleMin = DoubleStream.of(1.5, 2.5, 3.5, 4.5, 5.5).min();  // Minimum element
        log.info("DoubleStream Min: " + (doubleMin.isPresent() ? doubleMin.getAsDouble() : "N/A"));  // Output: DoubleStream Min: 1.5

        OptionalDouble doubleMax = DoubleStream.of(1.5, 2.5, 3.5, 4.5, 5.5).max();  // Maximum element
        log.info("DoubleStream Max: " + (doubleMax.isPresent() ? doubleMax.getAsDouble() : "N/A"));  // Output: DoubleStream Max: 5.5
    }
}
