package com.java8miusings.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ComposingPredicates {

	public static void main(String[] args) {
		ComposingPredicates cp = new ComposingPredicates();
		List<Product> products = Product.findAll();

		Predicate<Product> productMobilePredicate = p -> "Mobile".equalsIgnoreCase(p.getCategory());
		System.out.println("All Mobile Products");
		List<Product> mobileProducts = cp.filterProducts(products, productMobilePredicate);
		printList(mobileProducts, System.out::println);

		System.out.println("All Non-Mobile Products");
		List<Product> nonMobileProducts = cp.filterProducts(products, productMobilePredicate.negate());
		printList(nonMobileProducts, System.out::println);

		System.out.println("All Samsung-Mobile Products");
		Predicate<Product> productBrandPredicate = p -> "Samsung".equalsIgnoreCase(p.getBrandName());
		List<Product> samsungMobileProducts = cp.filterProducts(products,
				productMobilePredicate.and(productBrandPredicate));
		printList(samsungMobileProducts, System.out::println);

		System.out.println("All Samsung-Iphone-Mobile Products");
		List<Product> samsungIphoneMobileProducts = cp.filterProducts(products,
				productMobilePredicate.and(productBrandPredicate.or(p -> "Iphone".equalsIgnoreCase(p.getBrandName()))));
		printList(samsungIphoneMobileProducts, System.out::println);
	}

	public static <T> void printList(List<T> list, Consumer<T> consumer) {
		for (T element : list) {
			consumer.accept(element);
		}
	}

	public <T> List<T> filterProducts(List<T> tList, Predicate<T> p) {
		List<T> productsList = new ArrayList<T>();
		for (T t : tList) {
			if (p.test(t)) {
				productsList.add(t);
			}
		}
		return productsList;
	}

}
