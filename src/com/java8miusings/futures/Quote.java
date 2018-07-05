package com.java8miusings.futures;

public class Quote {

	private final String shopName;

	private final double price;

	private final Discount.Code discountCode;

	public Quote(String shopName, double price, Discount.Code discountCode) {
		this.shopName = shopName;
		this.price = price;
		this.discountCode = discountCode;
	}

	public static Quote parse(String shopPriceDisocuntString) {
		String[] shopPriceDiscount = shopPriceDisocuntString.split(":");
		String shopName = shopPriceDiscount[0];
		double price = Double.parseDouble(shopPriceDiscount[1]);
		Discount.Code discountCode = Discount.Code.valueOf(shopPriceDiscount[2]);
		return new Quote(shopName, price, discountCode);
	}

	public String getShopName() {
		return shopName;
	}

	public double getPrice() {
		return price;
	}

	public Discount.Code getDiscountCode() {
		return discountCode;
	}

}
