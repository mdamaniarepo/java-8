package com.java8miusings.futures;

import java.util.concurrent.Future;

public class CompletableFutureTest {

	public static void main(String[] args) {
		CompletableFutureTest completableFutureTest = new CompletableFutureTest();
		long start = System.nanoTime();
		System.out.println("Start Test");		
		completableFutureTest.findPrice(new Shop("Reebok"), "Sports Shoes");
		
		System.out.println("Start Supply Async Test");
		CompletableFutureTest completableFutureTest1 = new CompletableFutureTest();
		completableFutureTest1.findPriceBySupplyAsync(new Shop("Reebok"), "Sports Shoes");
		long end = System.nanoTime();
		System.out.printf("End Test: %s", (end - start) / 1_000_000);
		
		new Thread(() -> System.out.println("I am a thread")).start();
	}

	public void findPrice(Shop shop, String product) {
		long start = System.nanoTime();

		Future<Double> asyncPriceFinder = shop.getPriceAsync(product);
		long invocationTime = (System.nanoTime() - start) / 1_000_000;
		System.out.printf("Invoked (%s - %s) returned after: %s msecs \n", product, Thread.currentThread().getId(), invocationTime);

		// do something else here

		try {
			Double price = asyncPriceFinder.get();
			System.out.printf("Price is : %s \n", price);
		} catch (Exception e) {
			e.printStackTrace();
		}

		long retrievalTime = (System.nanoTime() - start) / 1_000_000;
		System.out.printf("Ended (%s - %s) returned after: %s msecs \n", product, Thread.currentThread().getId(), retrievalTime);
	}
	
	public void findPriceBySupplyAsync(Shop shop, String product) {
		long start = System.nanoTime();

		Future<Double> asyncPriceFinder = shop.getPriceSupplyAsync(product);
		long invocationTime = (System.nanoTime() - start) / 1_000_000;
		System.out.printf("Invoked (%s - %s) returned after: %s msecs \n", product, Thread.currentThread().getId(), invocationTime);

		// do something else here

		try {
			Double price = asyncPriceFinder.get();
			System.out.printf("Price is : %s \n", price);
		} catch (Exception e) {
			e.printStackTrace();
		}

		long retrievalTime = (System.nanoTime() - start) / 1_000_000;
		System.out.printf("Ended (%s - %s) returned after: %s msecs \n", product, Thread.currentThread().getId(), retrievalTime);
	}
}
