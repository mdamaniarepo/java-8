package com.java8miusings.futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class BlockToNonBlockingTest {

	private ExecutorService customExecutorService = Executors.newFixedThreadPool(10, new ThreadFactory() {
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r, "customExecutorServicePool");
			thread.setDaemon(true);
			return thread;
		}
	});
	
	
	private List<Shop> shops = new ArrayList<Shop>();
	{
		shops.add(new Shop("Ezone"));
		shops.add(new Shop("Croma"));
		shops.add(new Shop("Hypercity"));
		shops.add(new Shop("Inorbit"));
		shops.add(new Shop("Globus"));
	}

	public static void main(String[] args) {
		BlockToNonBlockingTest blockToNonBlockingTest = new BlockToNonBlockingTest();

		long start = System.nanoTime();
		System.out.println(blockToNonBlockingTest.findPrices("myPhone7S"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");

		start = System.nanoTime();
		System.out.println(blockToNonBlockingTest.findPricesUsingParallelStreams("myPhone7S"));
		duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");

		start = System.nanoTime();
		System.out.println(blockToNonBlockingTest.findPricesUsingCompletableFutures("myPhone7S"));
		duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");
		
		start = System.nanoTime();
		System.out.println(blockToNonBlockingTest.findPricesUsingCompletableFuturesExecutor("myPhone7S"));
		duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");

	}

	// This is a sequential stream execution of find price
	public List<String> findPrices(String productName) {
		return shops.stream()
				.map(shop -> String.format("\n Price of %s in shop: %s ", productName, shop.getPrice(productName)))
				.collect(Collectors.toList());
	}

	// This is a parallel stream execution of find price
	public List<String> findPricesUsingParallelStreams(String productName) {
		return shops.parallelStream()
				.map(shop -> String.format("\n Price of %s in shop: %s ", productName, shop.getPrice(productName)))
				.collect(Collectors.toList());
	}

	// This is a CompletableFuture execution of find price
	public List<String> findPricesUsingCompletableFutures(String productName) {
		List<CompletableFuture<String>> completableFutures = shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(
						() -> String.format("\n Price of %s in shop: %s ", productName, shop.getPrice(productName))))
				.collect(Collectors.toList());

		return completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
	}

	// This is a CompletableFuture execution of find price
	public List<String> findPricesUsingCompletableFuturesExecutor(String productName) {
		List<CompletableFuture<String>> completableFutures = shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(
						() -> String.format("\n Price of %s in shop: %s ", productName, shop.getPrice(productName)), customExecutorService))
				.collect(Collectors.toList());

		return completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
	}

}
