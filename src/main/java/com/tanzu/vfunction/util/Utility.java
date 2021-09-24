package com.tanzu.vfunction.util;

public class Utility {

	public static double getPriceAfterDiscount(final double price, final double discount) {
		return price - ((discount / 100) * price);
	}
}
