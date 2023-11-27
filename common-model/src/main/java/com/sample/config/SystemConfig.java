package com.sample.config;

import java.util.Random;

public class SystemConfig {

	private static final Long[] arr = {2000l, 3000l, 5000l};
	
	public static void sleep() {
		try {
			Thread.sleep(randomSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static long randomSleepTime() {
		return arr[new Random().nextInt(0, 3)];
	}
}
