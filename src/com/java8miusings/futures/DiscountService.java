package com.java8miusings.futures;

public interface DiscountService {
	
	String getPriceWithDiscountCode(String product);
	
	String getPriceWithDiscountCodeFromSlowService(String product);

}
