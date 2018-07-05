package com.java8miusings.lambdas;

public class ProductTVPredicateImpl implements ProductPredicate {

	@Override
	public boolean test(Product product) {
		return "TV".equalsIgnoreCase(product.getCategory());
	}

}
