package com.java8musings.streams;

import java.util.ArrayList;
import java.util.List;

public class Dish {

	private static List<Dish> dishes = new ArrayList<Dish>();

	static {
		dishes.add(new Dish("Pizza", 1000, "Other", Boolean.TRUE));
		dishes.add(new Dish("Seasonal Fruits", 100, "Other", Boolean.TRUE));
		dishes.add(new Dish("French Fries", 700, "Other", Boolean.TRUE));
		dishes.add(new Dish("Rice", 200, "Other", Boolean.TRUE));
		dishes.add(new Dish("Pork", 1200, "Meat", Boolean.FALSE));
		dishes.add(new Dish("Beef", 1500, "Meat", Boolean.FALSE));
		dishes.add(new Dish("Chicken", 300, "Meat", Boolean.FALSE));
		dishes.add(new Dish("Prawns", 500, "Fish", Boolean.FALSE));
		dishes.add(new Dish("Salmon", 300, "Fish", Boolean.FALSE));
		dishes.add(new Dish("Mackrel", 300, "Fish", Boolean.FALSE));
	}

	public Dish(String name, Integer calories, String type, boolean vegetarian) {
		this.name = name;
		this.calories = calories;
		this.type = type;
		this.vegetarian = vegetarian;
	}

	private String name;

	private Integer calories;

	private String type;

	private boolean vegetarian;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}
	
	@Override
	public String toString() {
		return String.format("%s - %s - %s - %s", name, calories, type, vegetarian);
	}

	public static List<Dish> findAll() {
		return dishes;
	}
}
