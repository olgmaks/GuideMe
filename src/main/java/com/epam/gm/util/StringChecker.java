package com.epam.gm.util;

import java.util.regex.Pattern;

public class StringChecker {

	public static boolean checkOnOnlyAlphabetsAndNumeric(String string) {
		boolean newBoolean = false;
		if (Pattern.matches("[a-zA-Z0-9]+", string)) {
			newBoolean = true;
		}
		return newBoolean;
	}

}
