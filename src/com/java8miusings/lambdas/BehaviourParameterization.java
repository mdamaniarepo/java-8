package com.java8miusings.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class BehaviourParameterization {

	public static void main(String[] args) {
		BehaviourParameterization behaviourParameterization = new BehaviourParameterization();

		printList(behaviourParameterization.filterMobilePhones(Product.findAll()));

		System.out.println("Filter by Category (TV)");
		printList(behaviourParameterization.filterProductsByCategory(Product.findAll(), "TV"));

		System.out.println("Filter by Category (Mobile)");
		printList(behaviourParameterization.filterProductsByCategory(Product.findAll(), "Mobile"));

		System.out.println("Filter by Brand (Samsuung)");
		printList(behaviourParameterization.filterProductsByBrand(Product.findAll(), "Samsung"));

		System.out.println("Filter by Category (TV)");
		printList(behaviourParameterization.filterProducts(Product.findAll(), new ProductTVPredicateImpl()));

		System.out.println("Filter by Category (Mobile)");
		printList(behaviourParameterization.filterProducts(Product.findAll(), new ProductPredicate() {
			@Override
			public boolean test(Product product) {
				return "Mobile".equalsIgnoreCase(product.getCategory());
			}
		}));

		System.out.println("Filter by Category (TV)");
		printList(behaviourParameterization.filterProducts(Product.findAll(),
				(Product product) -> "TV".equalsIgnoreCase(product.getCategory())));

		System.out.println("Filter by Brand (Samsung)");
		printList(behaviourParameterization.filterProducts(Product.findAll(),
				(Product product) -> "Samsung".equalsIgnoreCase(product.getBrandName())));
	}

	public List<Product> filterProductsByCategory(List<Product> products, String category) {
		List<Product> productsList = new ArrayList<Product>();
		for (Product product : products) {
			if (category.equalsIgnoreCase(product.getCategory())) {
				productsList.add(product);
			}
		}
		return productsList;
	}

	public List<Product> filterProductsByBrand(List<Product> products, String brandName) {
		List<Product> productsList = new ArrayList<Product>();
		for (Product product : products) {
			if (brandName.equalsIgnoreCase(product.getBrandName())) {
				productsList.add(product);
			}
		}
		return productsList;
	}

	public List<Product> filterMobilePhones(List<Product> products) {
		List<Product> productsList = new ArrayList<Product>();
		for (Product product : products) {
			if ("Mobile".equalsIgnoreCase(product.getCategory())) {
				productsList.add(product);
			}
		}
		return productsList;
	}

	public List<Product> filterProducts(List<Product> products, ProductPredicate p) {
		List<Product> productsList = new ArrayList<Product>();
		for (Product product : products) {
			if (p.test(product)) {
				productsList.add(product);
			}
		}
		return productsList;
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

	public static <T> void printList(List<T> someList) {
		for (T t : someList) {
			System.out.println(t.toString());
		}
	}

	public <T, U> Boolean filterProducts(List<T> tList, U u, BiPredicate<List<T>, U> p) {
		return p.test(tList, u);
	}

}
