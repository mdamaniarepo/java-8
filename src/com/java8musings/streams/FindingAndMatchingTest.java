package com.java8musings.streams;

import java.util.List;

/**
 * 
 * Another common data processing idiom is finding whether some elements in a
 * set of data match a given property.
 * 
 * The Streams API provides such facilities through the allMatch, anyMatch,
 * noneMatch, findFirst, and findAny methods of a stream.
 * 
 * @author mdama1
 *
 */
public class FindingAndMatchingTest {

	public static void main(String[] args) {
		List<Dish> menu = Dish.findAll();

		// Any match returns boolean hence it is a terminal operation.
		boolean containsVegetarian = menu.stream().anyMatch(Dish::isVegetarian);
		if (containsVegetarian) {
			System.out.println("Menu is somewhat vegetarian");
		}

		// All match matches all elements to check if the satisfy the match predicate
		boolean isVegetarian = menu.stream().allMatch(Dish::isVegetarian);
		if (isVegetarian) {
			System.out.println("Menu is not completely vegetarian");
		}
		
		// None match
		boolean isNonVegetarian = menu.stream().noneMatch(Dish::isVegetarian);
		if (isNonVegetarian) {
			System.out.println("Menu is not completely non-vegetarian");
		}
		
		// findFirst
		menu.stream().filter(Dish::isVegetarian).findFirst().ifPresent(System.out::println);

		// findAny
		menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(System.out::println);
	}

}
