package com.java8musings.streams;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReducingAndSummarizingCollectors {

	public static void main(String[] args) {
		List<Dish> menu = Dish.findAll();

		System.out.println("Count of number of dishes in Menu: " + menu.stream().collect(counting()));
		
		Optional<Dish> dishOptional = menu.stream().collect(maxBy(Comparator.comparing(Dish::getCalories)));
		System.out.println("Max caloric dish : " + dishOptional.get());
		
		dishOptional = menu.stream().collect(minBy(Comparator.comparing(Dish::getCalories)));
		System.out.println("Min caloric dish : " + dishOptional.get());
		
		// Summarizing operations
		
		int totalCaloriesInMenu = menu.stream().collect(summingInt(Dish::getCalories));
		System.out.println("Total calories in menu : " + totalCaloriesInMenu);
		
		double averageCaloriesInMenu = menu.stream().collect(averagingInt(Dish::getCalories));
		System.out.println("Average calories in menu : " + averageCaloriesInMenu);
		
		IntSummaryStatistics statistics = menu.stream().collect(summarizingInt(Dish::getCalories));
		System.out.println("Summary statistics of calories in menu : " + statistics);
		
		// Joining operations on String
		String menuItemsString = menu.stream().map(Dish::getName).collect(Collectors.joining());
		System.out.println("Menu Items by Name : " + menuItemsString);
		
		menuItemsString = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
		System.out.println("Menu Items by Name : " + menuItemsString);
		
		// reducing collections
		Optional<Integer> caloriesSum = menu.stream().map(Dish::getCalories).collect(Collectors.reducing((a, b) -> a + b));
		System.out.println("Sum of calories : " + caloriesSum.get());
	}

}
