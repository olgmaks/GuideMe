package com.epam.gm.util;

public class DataValidator {

	
	public static boolean isPositiveNumber(String string) {
		if (string == null)
			return false;
		
		if(string.matches("^-?\\d+$")) {
			if(Integer.valueOf(string) > 0) 
				return true;
		}
		
		return false;
	}

}
