package com.java8miusings.futures;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTest {
	
	public static ExecutorService executorService = Executors.newCachedThreadPool();
	
	public static void main(String[] args) {
		System.out.printf("Main : Id %d - %s\n", Thread.currentThread().getId(), Thread.currentThread().getName());
		
		Future<Double> future = executorService.submit(new Callable<Double>() {
			@Override
			public Double call() throws Exception {
				System.out.printf("Future Start : Id %d - %s\n", Thread.currentThread().getId(), Thread.currentThread().getName());
				Double result = doSomeLongComputation();
				return result;
			}
		});
		
		/*while (!future.isDone()) {
			//System.out.println("Future not done");
		}
		System.out.println("Yuppie! Future complete");*/
		
		try {
			Double result = future.get(6, TimeUnit.SECONDS);
			System.out.printf("Main : Id %d - result: %s\n", Thread.currentThread().getId(), result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	public static Double doSomeLongComputation() throws InterruptedException {
		Thread.sleep(5000);
		System.out.println("Returning long computation");
		
		int i = 1/2;
		System.out.println(i);
		return 1.0;
	}
	
	

}
