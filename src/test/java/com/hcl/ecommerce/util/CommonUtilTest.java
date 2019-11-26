package com.hcl.ecommerce.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CommonUtilTest {

	@Test
	public void testCalculateAverage() {
		List<Integer> ratings = new ArrayList<>();
		Double rating = CommonUtil.calculateAverage(ratings);
		assertEquals(0, rating);
	}
}
