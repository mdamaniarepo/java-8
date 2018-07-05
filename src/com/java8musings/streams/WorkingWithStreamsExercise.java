package com.java8musings.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class WorkingWithStreamsExercise {
	
	
	public static void main(String[] args) {
		
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		printList(numbers.stream().map(number -> number * 2).collect(Collectors.toList()), System.out::println);
		
		List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5);
		List<int[]> pairs = numbers.stream().flatMap(i -> numbers2.stream().map(j -> new int[] {i, j})).collect(Collectors.toList());
		pairs.forEach(pair -> System.out.println(pair[0] + "," + pair[1]));
		
		System.out.println("Printing all pairs divisible by 3");
		pairs = numbers.stream().flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[] {i, j})).collect(Collectors.toList());
		pairs.forEach(pair -> System.out.println(pair[0] + "," + pair[1]));
		
	}
	
	public static <T> void printList(List<T> someList, Consumer<T> consumer) {
		for (T t : someList) {
			consumer.accept(t);
		}
	}

}
