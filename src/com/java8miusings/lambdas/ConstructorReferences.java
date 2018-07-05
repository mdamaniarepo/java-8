package com.java8miusings.lambdas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConstructorReferences {

	private Map<String, Function<Long, CatalogEntity>> catalogEntities = new HashMap<>();
	
	
	{
		catalogEntities.put("sku", SKU::new);
		catalogEntities.put("product", Product::new);
	}

	public static void main(String[] args) {

		// Creating object using constructor references
		Supplier<ConstructorReferences> constructorReferencesSupplier = ConstructorReferences::new;
		ConstructorReferences constructorReferences = constructorReferencesSupplier.get();
		System.out.println("Current class object : " + constructorReferences);

		// Creating Object of one argument constructor
		Function<Long, Product> instantiateProductFunctionLambda = i -> new Product(i); // using lambda
		System.out.println("Product using Lambda: " + instantiateProductFunctionLambda.apply(6L));
		Function<Long, Product> instantiateProductFunction = Product::new; // using constructor reference
		System.out.println("Product using Constructor Reference: " + instantiateProductFunction.apply(7L));

		// Creating Object with 2 argument constructor
		BiFunction<Long, String, Product> instantiateProductBIFunctionLambda = (id, name) -> new Product(id, name);
		System.out.println("Product using Lambda: " + instantiateProductBIFunctionLambda.apply(8L, "Product 8"));
		BiFunction<Long, String, Product> instantiateBIProductFunction = Product::new;
		System.out
				.println("Product using Constructor Reference: " + instantiateBIProductFunction.apply(9L, "Product 9"));
		
		
		System.out.println("Product using factory method: " + constructorReferences.createInstance("product", 10L));
		System.out.println("SKU using factory method: " + constructorReferences.createInstance("sku", 11L));

	}

	public static <T> void printList(List<T> someList) {
		for (T t : someList) {
			System.out.println(t.toString());
		}
	}

	public <T, U> Boolean filterProducts(List<T> tList, U u, BiPredicate<List<T>, U> p) {
		return p.test(tList, u);
	}

	public static <T> void printList(List<T> list, Consumer<T> consumer) {
		for (T element : list) {
			consumer.accept(element);
		}
	}

	public CatalogEntity createInstance(String type, Long id) {
		Function<Long, CatalogEntity> catalogEntityInstantiationFunction = catalogEntities.get(type);
		return catalogEntityInstantiationFunction.apply(id);
	}

}
