package com.java8musings.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/***
 * Stream allow short circuiting and loop fusion Streams are like builder
 * patterns
 * 
 * Streams use internal iteration instead of external iteration and hence the
 * abstraction is useful to move from sequential to parallel and vice-versa
 * 
 * @author mdama1
 *
 */

public class StreamIntroduction {

	public static void main(String[] args) {
		StreamIntroduction streamIntroduction = new StreamIntroduction();

		List<Dish> menu = Dish.findAll();

		System.out.println("Low Caloric Dishes (Using Java 7)");
		List<String> lowCaloricdishes = streamIntroduction.filterAndSortLowCalorieDishes(menu, 400);
		printList(lowCaloricdishes, System.out::println);

		System.out.println("Low Caloric Dishes (Using Java 8)");
		lowCaloricdishes = menu.stream().filter(d -> d.getCalories() < 400)
				.sorted(Comparator.comparing(Dish::getCalories)).map(d -> d.getName()).collect(Collectors.toList());

		printList(lowCaloricdishes, System.out::println);
	}

	public List<String> filterAndSortLowCalorieDishes(List<Dish> menu, Integer calories) {
		List<Dish> lowCaloricDishes = new ArrayList<Dish>();
		for (Dish dish : menu) {
			if (dish.getCalories() < calories) {
				lowCaloricDishes.add(dish);
			}
		}

		// Sorting
		lowCaloricDishes.sort(new Comparator<Dish>() {
			@Override
			public int compare(Dish o1, Dish o2) {
				return o1.getCalories().compareTo(o2.getCalories());
			}
		});

		// Iterating and accumulating dish names
		List<String> lowCaloricDishNames = new ArrayList<String>();
		for (Dish dish : lowCaloricDishes) {
			if (dish.getCalories() < calories) {
				lowCaloricDishNames.add(dish.getName());
			}
		}

		return lowCaloricDishNames;
	}

	public static <T> void printList(List<T> someList, Consumer<T> consumer) {
		for (T t : someList) {
			consumer.accept(t);
		}
	}
}
