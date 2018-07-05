package com.java8miusings.futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class ComposingFuturesTest {

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
		ComposingFuturesTest composingFuturesTest = new ComposingFuturesTest();
		long start = System.nanoTime();
		System.out.println(composingFuturesTest.findPrices("myPhone7S"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");

		start = System.nanoTime();
		System.out.println(composingFuturesTest.findPricesParallelStream("myPhone7S"));
		duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");

		start = System.nanoTime();
		System.out.println(composingFuturesTest.findPricesAsync("myPhone7S"));
		duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");

		start = System.nanoTime();
		Future<Double> priceInUSD = composingFuturesTest.getPriceWithCombine("myPhone7S");
		try {
			System.out.println(priceInUSD.get(2, TimeUnit.SECONDS));
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");
		
		
		start = System.nanoTime();
		composingFuturesTest.findPricesWithRandomDelay("myPhone7S");
		duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");
	}

	// This is a sequential stream execution of find price
	public List<String> findPrices(String productName) {
		return shops.stream().map(shop -> shop.getPriceWithDiscountCode(productName)).map(Quote::parse)
				.map(Discount::applyDiscount).collect(Collectors.toList());
	}

	// This is a sequential stream execution of find price
	public List<String> findPricesParallelStream(String productName) {
		return shops.parallelStream().map(shop -> shop.getPriceWithDiscountCode(productName)).map(Quote::parse)
				.map(Discount::applyDiscount).collect(Collectors.toList());
	}

	// This is a sequential stream execution of find price
	public List<String> findPricesAsync(String productName) {
		List<CompletableFuture<String>> completableFutures = shops.stream()
				.map(shop -> CompletableFuture
						.supplyAsync(() -> shop.getPriceWithDiscountCode(productName), customExecutorService))
				.map(future -> future.thenApply(Quote::parse))
				.map(future -> future.thenCompose(quote -> CompletableFuture
						.supplyAsync(() -> Discount.applyDiscount(quote), customExecutorService)))
				.collect(Collectors.toList());

		return completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
	}

	public CompletableFuture<Double> getPriceWithCombine(String productName) {
		Shop shop = new Shop("CrazyShop");
		return CompletableFuture.supplyAsync(() -> shop.getPrice(productName)).thenCombine(
				CompletableFuture.supplyAsync(() -> ExchangeRateService.exchangeRate("someCurrency")), (p, e) -> p * e);

	}

	// This is a sequential stream execution of find price
	@SuppressWarnings("unchecked")
	public Void findPricesWithRandomDelay(String productName) {
		CompletableFuture<Void> [] completableFutures = shops.stream()
				.map(shop -> CompletableFuture
						.supplyAsync(() -> shop.getPriceWithDiscountCode(productName), customExecutorService))
				.map(future -> future.thenApply(Quote::parse))
				.map(future -> future.thenCompose(quote -> CompletableFuture
						.supplyAsync(() -> Discount.applyDiscount(quote), customExecutorService)))
				.map(future -> future.thenAccept(System.out::println))
				.toArray(size -> new CompletableFuture[size]);
		
		return CompletableFuture.allOf(completableFutures).join();
	}

}
