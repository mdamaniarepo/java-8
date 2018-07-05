package com.java8musings.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * 
 * A query that combines elements if a stream to generate/produce a single value
 * is call reduction/folding
 * 
 * 
 * 
 * @author mdama1
 *
 */
public class ReducingTest {

	public static void main(String[] args) {
		List<Dish> menu = Dish.findAll();

		// reduce takes 2 operators initial value and BinaryOperator
		Integer sumOfCalories = menu.stream().map(Dish::getCalories).reduce(0, (a, b) -> a + b);
		System.out.println("Sum of calories : " + sumOfCalories);

		sumOfCalories = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
		System.out.println("Sum of calories : " + sumOfCalories);
		
		System.out.printf("Factorial of number: %s is %s \n", 5,  ReducingTest.factorial(5));
		
		System.out.printf("Factorial of number: %s is %s \n", 6,  ReducingTest.factorialUsingNumericRange(6));
		
		// Another variant of reduce which returns an Optional as it accepts no default/initial value
		List<Integer> numbersList = Arrays.asList(1, 2, 3, 4, 5, 6);
		Optional<Integer> optionalMin = numbersList.stream().reduce(Integer::min);
		optionalMin.ifPresent(System.out::println);
		
		Optional<Integer> optionalMax = numbersList.stream().reduce(Integer::max);
		optionalMax.ifPresent(System.out::println);
		
		System.out.println(IntStream.range(1, 100).filter(i -> i % 2 == 0).count());
	}

	public static Integer factorial(Integer number) {
		List<Integer> integers = new ArrayList<Integer>();
		for (int i = 1; i <= number; i++) {
			integers.add(i);
		}
		return integers.stream().reduce(1, (a, b) -> a * b);
	}
	
	// Using numeric range for factorial
	public static Integer factorialUsingNumericRange(Integer number) {
		return IntStream.rangeClosed(1, number).reduce(1, (a, b) -> a * b);
	}
	

}
