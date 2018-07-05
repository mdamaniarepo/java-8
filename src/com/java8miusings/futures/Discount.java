package com.java8miusings.futures;

public class Discount {

	public enum Code {
		NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

		private final int percentage;

		private Code(int percentage) {
			this.percentage = percentage;
		}

		public int getPercentage() {
			return percentage;
		}
	}

	public static String applyDiscount(Quote quote) {
		return "\n" + quote.getShopName() + " price is " + Discount.apply(quote) + " discount code applied : " + quote.getDiscountCode();
	}
	
	public static double apply(Quote quote) {
		delay();
		return quote.getPrice() * (100 - quote.getDiscountCode().getPercentage()) / 100;
	}

	public static void delay() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
