package com.java8musings.streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;

/**
 * Collectors are broadly divided into 3 parts
 * 
 * 1.	Reducing and Summarizing stream to a single value
 * 2.	Grouping elements
 * 3.	Partitioning elements
 * 
 * @author mdama1
 *
 */
public class CollectingDataWithStreamsTest {

	public static void main(String[] args) {
		List<Dish> menu = Dish.findAll();

		// pre java 8 code to group all dishes based on type
		Map<String, List<Dish>> dishTypeMap = getDishesByType(menu);
		System.out.println("Dish by type (Pre Java 8): " + dishTypeMap);
		
		//with java 8
		dishTypeMap = menu.stream().collect(groupingBy(Dish::getType));
		System.out.println("Dish by type (Java 8): " + dishTypeMap);
	}

	private static Map<String, List<Dish>> getDishesByType(List<Dish> menu) {
		Map<String, List<Dish>> dishTypeMap = new HashMap<String, List<Dish>>();
		List<Dish> dishes = null;
		for (Dish dish : menu) {
			dishes = dishTypeMap.get(dish.getType());
			if (null == dishes) {
				dishes = new ArrayList<Dish>();
				dishTypeMap.put(dish.getType(), dishes);
			}
			dishes.add(dish);
		}
		return dishTypeMap;
	}

}
