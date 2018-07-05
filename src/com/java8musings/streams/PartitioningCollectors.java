package com.java8musings.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningCollectors {

	public static void main(String[] args) {
		List<Dish> menu = Dish.findAll();

		Map<Boolean, List<Dish>> menuDishesMap = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
		System.out.println("Vegetarian: " + menuDishesMap.get(true) + "\nNon Vegetarian: "+ menuDishesMap.get(false));
	}

}
