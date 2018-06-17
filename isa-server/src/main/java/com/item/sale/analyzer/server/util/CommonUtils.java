package com.item.sale.analyzer.server.util;

import java.util.Random;

/**
 * CommonUtils - this class contains all the utility methods.
 * @author Siddharth Kar
 *
 */

public final class CommonUtils {
	
	private static final int QUANTITY_LIMIT = 19;
	
	/**
	 * randomCategory - returns a random category from a list of categories
	 * 
	 * @returns a random category
	 */
	public static String randomCategory() {
		String[] categories = "grocery,stationary,electronics,home,personal care".split(",");
		return categories[new Random().nextInt(categories.length)];
	}
	
	/**
	 * randomVendor - returns a random vendor from a list of vendors
	 * 
	 * @returns a random category
	 */
	public static String randomVendor() {
		String[] vendors = "walmart,flipkart,amazon,snapdeal,tata cliq".split(",");
		return vendors[new Random().nextInt(vendors.length)];
	}
	
	/**
	 * randomVendor - returns a random vendor from a list of vendors
	 * 
	 * @returns a random category
	 */
	public static int randomQuantity() {
		return new Random().nextInt(QUANTITY_LIMIT) + 1;
	}
}
