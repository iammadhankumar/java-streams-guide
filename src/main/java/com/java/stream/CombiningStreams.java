package com.java.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

/**
 * Combining Streams Example
 * This class demonstrates how to concatenate multiple streams using Stream.concat().
 * 
 * Author: MadhanKumar
 */
@Slf4j
public class CombiningStreams {

    public static void main(String[] args) {
        // Create two lists of strings
        List<String> list1 = Arrays.asList("Apple", "Banana", "Orange");
        List<String> list2 = Arrays.asList("Grapes", "Mango", "Peach");

        // Create streams from the lists
        Stream<String> stream1 = list1.stream();
        Stream<String> stream2 = list2.stream();

        // Combine the streams using Stream.concat()
        Stream<String> combinedStream = Stream.concat(stream1, stream2);

        // Collect the combined stream into a list
        List<String> combinedList = combinedStream.collect(Collectors.toList());

        // Display the combined result
        log.info("Combined List: " + combinedList);
    }
}

