package com.java8miusings.lambdas;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class ComposingComparator {
	
	public static void main(String[] args) {
		List<Product> products = Product.findAll();
		
		// Comparator.reversed
		Comparator<Product> reverseRankComparator = Comparator.comparing(Product::getRank).reversed();
		System.out.println("Products Sorted By Rank (Descending) using comparing.reversed");
		products.sort(reverseRankComparator);
		printList(products, System.out::println);
		
		// Chaining comparators
		System.out.println("Products Sorted By Rank and Category");
		Comparator<Product> rankAndCategoryComparator = reverseRankComparator.thenComparing(Product::getBrandName);
		products.sort(rankAndCategoryComparator);
		printList(products, System.out::println);
	}
	
	
	public static <T> void printList(List<T> list, Consumer<T> consumer) {
		for (T element : list) {
			consumer.accept(element);
		}
	}

}
