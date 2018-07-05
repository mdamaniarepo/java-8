package com.java8miusings.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class MethodReferencesTest {

	public static void main(String[] args) {
		MethodReferencesTest methodReferencesTest = new MethodReferencesTest();
		
		// Method References
		List<String> alphabetsList = Arrays.asList("a", "b", "A", "B");
		// lambda expression
		alphabetsList.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
		// method signature
		alphabetsList.sort(String::compareToIgnoreCase);
		printList(alphabetsList, System.out::println);

		BiPredicate<List<String>, String> biPredicateContains = List::contains;
		System.out.println(methodReferencesTest.filterProducts(alphabetsList, "A", biPredicateContains));
		System.out.println(methodReferencesTest.filterProducts(alphabetsList, "C", biPredicateContains.negate()));
		System.out.println(biPredicateContains.test(alphabetsList, "b"));
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

}
