package com.epam.gm.util;

import com.epam.gm.model.User;

//gryn
//Проміжна утилітка для валідації, поки захардкоджено
//Пізніше будуть юзатись анотації
public class ValidateHelper {
	
	public static String validateForScripts(String value) {
		
		if(value != null){
			String temp = value.toLowerCase().replaceAll(" ", "").replaceAll("\t", "").replaceAll("\n", "");
			if(temp.contains("<script")) 
				return "validation.containsscripts";
		}
		return "";
	}
	
	public static String validateField(String fieldName, String value, Class<?> clazz) {
		String result = validateForScripts(value);
		
		if(!"".equals(value))
			return result;
		
		if(clazz == User.class) {
			if(fieldName.equals("firstName")) {
				
				if(value.trim().length() == 0) 
					result = "validation.firstName.empty";
				else 
					result = "validation.ok";
			
			}
			
			if(fieldName.equals("lastName")) {
				
			}
			
			
			if(fieldName.equals("email")) {
				
				//if(1==1) return "hohoho";
				
				if(value.trim().length() == 0) 
					result = "validation.email.empty";
				else if(!value.contains("@")) {
					result = "validation.email.wrongformat";
				} else 
					result = "validation.ok";
				
			}
			
			if(fieldName.equals("password")) {
				
				if(value.trim().length() == 0) 
					result = "validation.password.empty";
				else if(value.trim().length() < 3) {
					result = "validation.password.toosmall";
				} else 
					result = "validation.ok";
				
			}


			if(fieldName.equals("sex")) {
				if(value.trim().length() == 0) 
					result = "validation.sex.empty";
				else if("male".equals(value.trim()) ||  "female".equals(value.trim())) {
					
					result = "validation.ok";
				} else 
					result = "validation.sex.wrongformat";
			}


			if(fieldName.equals("cellNumber")) {
				
			}
			
		}
		
		
		return result;
	}
	

	private static String[] userFields = "email firstName lastName sex cellNumber password".split(" ");
	
	public static String[] getArrayOfFields(Class<?> clazz) {
		
		if(clazz == User.class) {
			return userFields;
		}
		
		return null;
	}
	
	
	
}
