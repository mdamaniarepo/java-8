package com.java8miusings.futures;

import java.util.concurrent.Future;

public interface Price {

	public Double getPrice(String product) throws Exception;
	
	public Future<Double> getPriceAsync(String product);
	
	public Future<Double> getPriceSupplyAsync(String product);

}
