package com.hcl.ecommerce.util;

import java.util.List;
import java.util.OptionalDouble;

public class CommonUtil {

	private CommonUtil() {
	}

	/**
	 * calculateAverage
	 * 
	 * @param ratings
	 * @return
	 */
	public static Double calculateAverage(List<Integer> ratings) {
		OptionalDouble average = ratings.stream().mapToDouble(rating -> rating).average();
		return average.isPresent() ? average.getAsDouble() : 0;
	}
}
