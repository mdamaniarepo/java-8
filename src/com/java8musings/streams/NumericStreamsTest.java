package com.java8musings.streams;

import java.util.List;
import java.util.OptionalInt;

public class NumericStreamsTest {

	public static void main(String[] args) {

		List<Dish> menu = Dish.findAll();

		// calculate number of calories in menu
		// the cost of boxing the operation is an overhead and can cause a performance
		// overhead
		int calories = menu.stream().map(d -> d.getCalories()).reduce(0, Integer::sum);
		System.out.println("Total calories : " + calories);

		// better way of doing it
		calories = menu.stream().mapToInt(d -> d.getCalories()).sum();
		System.out.println("Total calories : " + calories);

		// better way of doing it
		OptionalInt caloriesOptionalInt = menu.stream().mapToInt(d -> d.getCalories()).max();
		System.out.println("Max calories in a dish : " + caloriesOptionalInt.orElse(0));

	}

}
