package com.java8musings.streams;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

import java.util.Comparator;

public class GroupingCollectors {

	public static void main(String[] args) {
		List<Dish> menu = Dish.findAll();

		// Single level grouping
		Map<String, List<Dish>> dishByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
		System.out.println("Group By: " + dishByType);

		// Multi level grouping

		Map<String, Map<String, List<Dish>>> typeAndCaloriesMap = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(d -> {
					if (d.getCalories() <= 400)
						return "LOW";
					else if (d.getCalories() <= 700)
						return "NORMAL";
					else
						return "FAT";
				})));

		System.out.println("Multi level Group By: " + typeAndCaloriesMap);

		// Grouping Data in Sub groups

		// find number of dish of each type using group by (type, count(*))
		System.out.println("Group by type and count: " + menu.stream().collect(groupingBy(Dish::getType, counting())));

		// Optional here is no useful as the Optional will only be added if the value of the key is found in the stream
		System.out.println("Group by type and max calorie dish: "
				+ menu.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparing(Dish::getCalories)))));
		
		// Transforming Optional<Dish> into a Dish
		System.out.println("Group by type and max calorie dish: "
				+ menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)), Optional::get))));
	}

}
