package com.logigear.driver;

import java.util.Arrays;

public enum DriverType {
	Chrome, Firefox, IE, Edge, Safari;

	public static String asString() {
		return Arrays.toString(DriverType.values()).replaceAll("^.|.$", "");
	}
}
