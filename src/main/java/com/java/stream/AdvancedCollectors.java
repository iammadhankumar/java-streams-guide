package com.java.stream;

import java.util.*;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * Advanced Collectors Example
 * This class demonstrates the usage of various advanced collectors provided by the Collectors class
 * to handle reduction operations in Java Streams.
 * 
 * Author: MadhanKumar
 */
@Slf4j
public class AdvancedCollectors {

    public static void main(String[] args) {
        // Sample data: a list of fruits
        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange", "Grapes", "Banana", "Mango", "Peach", "Orange");

        // 1. toList(): Collecting elements into a List
        List<String> fruitList = fruits.stream()
                .collect(Collectors.toList());
        log.info("Fruit List: " + fruitList);

        // 2. toSet(): Collecting elements into a Set (removing duplicates)
        Set<String> fruitSet = fruits.stream()
                .collect(Collectors.toSet());
        log.info("Fruit Set: " + fruitSet);

        // 3. toMap(): Collecting elements into a Map
        Map<String, Integer> fruitMap = fruits.stream().distinct()
                .collect(Collectors.toMap(fruit -> fruit, String::length));
        log.info("Fruit Map (Fruit Name -> Length): " + fruitMap);

        // 4. joining(): Concatenating elements into a single String
        String fruitString = fruits.stream()
                .collect(Collectors.joining(", ", "[", "]"));
        log.info("Joined Fruit String: " + fruitString);

        // 5. groupingBy(): Grouping elements by a classifier function
        Map<Integer, List<String>> groupedByLength = fruits.stream()
                .collect(Collectors.groupingBy(String::length));
        log.info("Grouped by Length: " + groupedByLength);

        // 6. partitioningBy(): Splitting elements into two groups based on a predicate
        Map<Boolean, List<String>> partitionedByLength = fruits.stream()
                .collect(Collectors.partitioningBy(fruit -> fruit.length() > 5));
        log.info("Partitioned by Length > 5: " + partitionedByLength);
    }
}

