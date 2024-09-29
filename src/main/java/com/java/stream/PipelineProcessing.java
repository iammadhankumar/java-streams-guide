package com.java.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

/**
 * Pipeline Processing
 * Demonstrates various pipeline processing techniques using Java Streams.
 * This class includes examples of stream creation, intermediate operations, 
 * terminal operations, and parallel processing.
 * 
 * Author: MadhanKumar
 */
@Slf4j
public class PipelineProcessing {

    public static void main(String[] args) {
        PipelineProcessing.streamCreation();
        PipelineProcessing.intermediateOperations();
        PipelineProcessing.terminalOperations();
    }

    /**
     *  Stream Creation
     *  Demonstrates how to create streams from different sources like lists and arrays.
     *  This method shows multiple ways to generate a stream and logs the output for each.
     */
    private static void streamCreation() {
        // Create stream from a list
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        Stream<String> streamFromList = names.stream();
        log.info("Create stream from a list: {}", streamFromList);

        // Create stream from an array
        String[] nameArray = {"Alice", "Bob", "Charlie"};
        Stream<String> streamFromArray = Arrays.stream(nameArray);
        log.info("Create stream from an array: {}", streamFromArray);

        // Create a stream using Stream.of()
        Stream<String> streamFromValues = Stream.of("Alice", "Bob", "Charlie");
        log.info("Create a stream using Stream.of(): {}", streamFromValues);
    }

    /**
     * Intermediate Operations
     * Demonstrates intermediate operations like filter, map, flatMap, sorted, distinct, limit, skip, and peek.
     * These operations modify the stream without consuming it, allowing further processing.
     */
    private static void intermediateOperations() {
        // filter - Filter stream elements based on a condition (names that start with 'A')
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Anna");
        names.stream().filter(name -> name.startsWith("A")).forEach(System.out::println);
        log.info("Filtered elements: {}", names);

        // Map - Map elements to their uppercase representation
        names = Arrays.asList("Alice", "Bob", "Charlie");
        names.stream().map(String::toUpperCase).forEach(System.out::println);
        log.info("Mapped elements to uppercase: {}", names);

        // flatMap - Flatten a list of lists into a single stream of elements
        List<List<String>> nestedList = Arrays.asList(Arrays.asList("Alice", "Bob"), Arrays.asList("Charlie", "David"));
        nestedList.stream().flatMap(List::stream).forEach(System.out::println);
        log.info("Flattened list: {}", nestedList);

        // sorted - Sort stream elements in natural order
        names = Arrays.asList("Charlie", "Bob", "Alice");
        names.stream().sorted().forEach(System.out::println);
        log.info("Sorted elements: {}", names);

        // distinct - Remove duplicates from the stream
        names = Arrays.asList("Alice", "Bob", "Alice", "Charlie");
        names.stream().distinct().forEach(System.out::println);

        // limit - Limit the stream to a specific number of elements
        names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        names.stream().limit(2).forEach(System.out::println);

        // skip - Skip a specific number of elements in the stream
        names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        names.stream().skip(2).forEach(System.out::println);

        // peek - Peek into each element in the stream before and after transformation
        names = Arrays.asList("Alice", "Bob", "Charlie");
        names.stream().peek(name -> log.info("Before: " + name))
                .map(String::toUpperCase)
                .peek(name -> log.info("After: " + name))
                .forEach(System.out::println);
    }

    /**
     *  Terminal Operations
     *  Demonstrates terminal operations like collect, forEach, reduce, count, findFirst, and parallelStream.
     *  These operations produce a result or a side-effect after processing the stream.
     */
    private static void terminalOperations() {
        // Collects elements into a container (like a List or Set)
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // collect - Collect the elements into a List
        List<String> nameList = names.stream().collect(Collectors.toList());
        Set<String> nameSet = names.stream().collect(Collectors.toSet());
        log.info("Collected to List: {}", nameList);
        log.info("Collected to Set: {}", nameSet);

        // forEach - Perform an action for each element of the stream
        names.stream().forEach(System.out::println);

        // reduce - Combine elements into a single result using a binary operator
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream().reduce(0, Integer::sum);
        log.info("Reduced sum: {}", sum);

        // count - Return the number of elements in the stream
        long count = names.stream().count();
        log.info("Count of elements: {}", count);

        // findFirst - Return the first element in the stream, if present
        Optional<String> firstName = names.stream().findFirst();
        firstName.ifPresent(name -> log.info("First element: {}", name));

        // parallelStream - Enable parallel processing of elements in the stream
        names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        names.parallelStream().map(String::toUpperCase).forEach(System.out::println);

        // AnyMatch - Check if any element in the stream matches a given predicate
        boolean hasCharlie = names.stream().anyMatch(name -> name.equals("Charlie"));
        log.info("Has 'Charlie': {}", hasCharlie);

        // allMatch - Check if all elements match a given predicate
        boolean allStartWithA = names.stream().allMatch(name -> name.startsWith("A"));
        log.info("All start with 'A': {}", allStartWithA);

        // noneMatch - Check if no elements match a given predicate
        boolean noStartsWithD = names.stream().noneMatch(name -> name.startsWith("D"));
        log.info("No starts with 'D': {}", noStartsWithD);
    }
}
