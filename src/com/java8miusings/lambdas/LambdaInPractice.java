package com.java8miusings.lambdas;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class LambdaInPractice {
	
	public static void main(String[] args) {
		
		List<Product> products = Product.findAll();
		products.sort(new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				return p1.getRank().compareTo(p2.getRank());
			}
		});
		
		System.out.println("Products Sorted By Rank (Ascending)");
		printList(products, System.out::println);
		
		System.out.println("Products Sorted By Rank (Descending)");
		products.sort((p1, p2) -> p2.getRank().compareTo(p1.getRank()));  // Note compiler infers the type automatically
		printList(products, System.out::println);
		
		System.out.println("Products Sorted By Rank (Ascending) using comparing");
		Comparator<Product> comparator = Comparator.comparing(Product::getRank);
		products.sort(comparator);
		printList(products, System.out::println);
	}
	
	public static <T> void printList(List<T> list, Consumer<T> consumer) {
		for (T element : list) {
			consumer.accept(element);
		}
	}

}
