package com.java8miusings.futures;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop implements Price, DiscountService {

	private Random random = new Random();

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Shop(String name) {
		this.name = name;
	}

	@Override
	public Double getPrice(String product) {
		System.out.println("Calculating Price");
		return calculatePrice(product);
	}

	@Override
	public String getPriceWithDiscountCodeFromSlowService(String product) {
		Double price = calculatePriceWithRandomDelay(product);
		Discount.Code discountCode = Discount.Code.values()[price.intValue() % 5];
		return String.format("%s:%s:%s", getName(), price, discountCode);
	}

	@Override
	public Future<Double> getPriceAsync(String product) {
		CompletableFuture<Double> asyncPriceResult = new CompletableFuture<>();
		new Thread(() -> {
			System.out.printf("Started thread Name: %s - %s\n", Thread.currentThread().getName(),
					Thread.currentThread().getId());
			try {
				Double price = calculatePrice(product);
				asyncPriceResult.complete(price);
			} catch (Exception e) {
				asyncPriceResult.completeExceptionally(e);
			}
			System.out.printf("Ended thread: %s - %s\n", Thread.currentThread().getName(),
					Thread.currentThread().getId());
		}).start();
		return asyncPriceResult;
	}

	@Override
	public Future<Double> getPriceSupplyAsync(String product) {
		return CompletableFuture.supplyAsync(() -> {
			System.out.printf("Started thread Name: %s - %s\n", Thread.currentThread().getName(),
					Thread.currentThread().getId());
			Double result = 0.0;
			try {
				result = calculatePrice(product);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			System.out.printf("Ended thread Name: %s - %s\n", Thread.currentThread().getName(),
					Thread.currentThread().getId());
			return result;
		});
	}

	public static void delay(Long delayInterval) {
		try {
			Thread.sleep(delayInterval);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private double calculatePrice(String product) {
		if ("Product_NOT_FOUND".equalsIgnoreCase(product)) {
			throw new RuntimeException("Product Not Found");
		}
		delay(1000L);
		return 10.0 * product.charAt(0) + name.charAt(1);
	}
	
	
	private double calculatePriceWithRandomDelay(String product) {
		if ("Product_NOT_FOUND".equalsIgnoreCase(product)) {
			throw new RuntimeException("Product Not Found");
		}
		delay(500L + random.nextInt(2000));
		return 10.0 * product.charAt(0) + name.charAt(1);
	}

	@Override
	public String getPriceWithDiscountCode(String product) {
		Double price = getPrice(product);
		Discount.Code discountCode = Discount.Code.values()[price.intValue() % 5];
		return String.format("%s:%s:%s", getName(), price, discountCode);
	}

}
