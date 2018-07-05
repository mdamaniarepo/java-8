package com.java8miusings.futures;

public class ExchangeRateService {
	
	public static Double exchangeRate(String currency) {
		System.out.println("Getting exchange rate");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 1.0;
	}

}
