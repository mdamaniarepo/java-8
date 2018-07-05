package com.java8miusings.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Functional interfaces work with reference types as it is a limitation with
 * how the generics work in java
 * 
 * Converting primitive type to reference type is called boxing Converting
 * reference type to corresponding primitive type is called unboxing.
 * 
 * Java also has an autoboxing mechanism to facilitate the task for programmers:
 * boxing and unboxing operations are done automatically
 * 
 * @author mdama1
 *
 */
public class FunctionalInterfacesTest {

	public static void main(String[] args) {
		BehaviourParameterization behaviourParameterization = new BehaviourParameterization();

		System.out.println("Filter by Category(TV)");
		Predicate<Product> productCategoryPredicate = (Product product) -> "Mobile"
				.equalsIgnoreCase(product.getCategory());
		printList(behaviourParameterization.filterProducts(Product.findAll(), productCategoryPredicate),
				(Product product) -> System.out.println(product.toString()));

		System.out.println("Filter by Brand(Samsung)");
		Predicate<Product> productBrandPredicate = (Product product) -> "Samsung"
				.equalsIgnoreCase(product.getBrandName());
		printList(behaviourParameterization.filterProducts(Product.findAll(), productBrandPredicate),
				(Product product) -> System.out.println(product.toString()));

		System.out.println("Print product rank");
		List<Long> productRanks = map(Product.findAll(), (Product p) -> p.getRank());
		printList(productRanks, (Long i) -> System.out.println("Product Rank: " + i));
		
	}

	public static <T> void printList(List<T> list, Consumer<T> consumer) {
		for (T element : list) {
			consumer.accept(element);
		}
	}

	public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
		List<R> results = new ArrayList<>();
		for (T element : list) {
			results.add(function.apply(element));
		}
		return results;
	}

}
