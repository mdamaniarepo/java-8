package com.java8musings.streams;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TestingStreamsExercise {

	public static void main(String[] args) {
		List<Transaction> transactions = Transaction.findAll();

		System.out.println("Transactions filtered by Year and Sorted(small to high) by value");
		List<Transaction> transactionsByYearAndValue = transactions.stream().filter(t -> t.getYear() == 2011)
				.sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
		printList(transactionsByYearAndValue, System.out::println);

		System.out.println("What are all the unique cities where the traders work?");
		List<String> distinctCities = transactions.stream().map(transaction -> transaction.getTrader().getCity())
				.distinct().collect(Collectors.toList());
		printList(distinctCities, System.out::println);

		System.out.println("Find all traders from Cambridge and sort them by name.");
		List<Trader> tradersInCambridgeSorted = transactions.stream()
				.filter(transaction -> "Cambridge".equalsIgnoreCase(transaction.getTrader().getCity()))
				.map(Transaction::getTrader).sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
		printList(tradersInCambridgeSorted, System.out::println);

		System.out.println("Return a string of all traders’ names sorted alphabetically");
		List<String> tradersSorted = transactions.stream().map(Transaction::getTrader).map(Trader::getName)
				.sorted(String::compareTo).collect(Collectors.toList());
		printList(tradersSorted, System.out::println);

		System.out.println("Are any traders based in Milan? Answer: "
				+ transactions.stream().map(Transaction::getTrader).anyMatch(t -> "Milan".equals(t.getCity())));

		System.out.println("Print all transactions’ values from the traders living in Cambridge.");
		transactions.stream()
				.filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
				.map(Transaction::getValue)
				.forEach(System.out::println);
		//printList(transactionValues, System.out::println);
		
		System.out.println("What’s the highest value of all the transactions?");
		Optional<Integer> maxValueOptional = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
		System.out.println("Max Value Transaction is: " + maxValueOptional.get());
		
		System.out.println("Find the transaction with the smallest value.");
		Optional<Integer> minValueOptional = transactions.stream().map(Transaction::getValue).reduce(Integer::min);
		System.out.println("Min Value Transaction is: " + minValueOptional.get());
	}

	public static <T> void printList(List<T> someList, Consumer<T> consumer) {
		for (T t : someList) {
			consumer.accept(t);
		}
	}

}
