package com.java8musings.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class WorkingWithStreams {

	public static void main(String[] args) {
		// WorkingWithStreams workingWithStreams = new WorkingWithStreams();

		List<Dish> menu = Dish.findAll();

		// filtering with predicate
		System.out.println("Filter with Predicate ****************************************");
		List<Dish> allVegetarianDishes = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
		printList(allVegetarianDishes, System.out::println);

		// finding distinct elements from list
		System.out.println("Distinct elements ********************************************");
		List<Integer> integers = Arrays.asList(1, 2, 3, 2, 4, 5, 4, 1);
		integers.stream().distinct().forEach(System.out::println); // distinct is based on hashcode and equals method

		// Truncating elements in a list
		System.out.println("Truncating elements ********************************************");
		allVegetarianDishes = menu.stream().filter(d -> d.getCalories() > 400).limit(2).collect(Collectors.toList());
		printList(allVegetarianDishes, System.out::println);

		// Skipping elements in a list
		System.out.println("Skipping elements ********************************************");
		allVegetarianDishes = menu.stream().filter(d -> d.getCalories() > 400).skip(2).collect(Collectors.toList());
		printList(allVegetarianDishes, System.out::println);
		
		System.out.println("Mapping  and chaining elements ********************************************");
		// Map applies a function to each element
		List<Integer> dishLengths = menu.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());
		printList(dishLengths, System.out::println);
		
		System.out.println("Flat maps ********************************************");
		// Exercise is to find all unique characters in a array output should be : (H, e, l, o, W, r, d)
		List<String> words = Arrays.asList("Hello", "World");
		printList(words.stream().map(s -> s.split("")).distinct().collect(Collectors.toList()), System.out::println);
		
		printList(words.stream().map(s -> s.split("")).map(Arrays::stream).distinct().collect(Collectors.toList()), System.out::println);
		
		printList(words.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList()), System.out::println);
		
	}

	public static <T> void printList(List<T> someList, Consumer<T> consumer) {
		for (T t : someList) {
			consumer.accept(t);
		}
	}

}
