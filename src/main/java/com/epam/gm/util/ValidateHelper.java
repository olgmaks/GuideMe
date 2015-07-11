package com.epam.gm.util;

import java.sql.SQLException;

import com.epam.gm.model.User;
import com.epam.gm.services.UserService;

//gryn
//Проміжна утилітка для валідації, поки захардкоджено
//Пізніше будуть юзатись анотації
public class ValidateHelper {
	
	private static final int MAX_LENGTH = 30;
	
	public static String validateForScripts(String value) {
		
		if(value != null){
			String temp = value.toLowerCase().replaceAll(" ", "").replaceAll("\t", "").replaceAll("\n", "");
			if(temp.contains("<")) 
				return "js.valid.containsscripts";
		}
		return "";
	}
	
	public static String validateField(String fieldName, String value, Class<?> clazz) {
		String result = validateForScripts(value);
		
		if(!"".equals(result))
			return result;
		
		if(clazz == User.class) {
			if(fieldName.equals("firstName")) {
				
				if(value.trim().length() == 0) 
					result = "js.valid.firstName.empty";
				
				else if(value.trim().length() > MAX_LENGTH) 
					result = "js.valid.stringtoolong";	
				else 
					result = "js.valid.ok";
			
			}
			
			if(fieldName.equals("lastName")) {
				if(value.trim().length() > MAX_LENGTH) 
					result = "js.valid.stringtoolong";
			}
			
			if(fieldName.equals("address")) {
				if(value.trim().length() > MAX_LENGTH) 
					result = "js.valid.stringtoolong";
			}			
			
			if(fieldName.equals("email")) {
				
				//if(1==1) return "hohoho";
				
				if(value.trim().length() == 0) 
					result = "js.valid.email.empty";
				else if(value.trim().length() > 50) 
					result = "js.valid.stringtoolong";	
				else if(! new EmailValidator().validate(value.trim())) {
					result = "js.valid.email.wrongformat";
				} else {
					User temp = null;
					try {
						temp = new UserService().getUserByEmail(value.trim().toLowerCase());
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(temp == null)
						result = "js.valid.ok";
					else
						result = "js.valid.email.exists";
				}
					
				
			}
			
			if(fieldName.equals("password")) {
				
				if(value.trim().length() == 0) 
					result = "js.valid.password.empty";
				else if(value.trim().length() > MAX_LENGTH) 
					result = "js.valid.stringtoolong";
				else if(value.contains("\t") ||  value.contains("\n"))
					result = "js.valid.containsscripts";
				else if(value.startsWith(" ") ||  value.endsWith(" "))
					result = "js.valid.password.startendspace";
				else if(value.trim().length() < 4) {
					result = "js.valid.password.toosmall";
				} else 
					result = "js.valid.ok";
				
			}
			
			if(fieldName.equals("cityId")) {
				
				
				
				if(ValidateHelper.isNumber(value.trim()))
					result = "js.valid.cityId.ok";
				else
					result = "js.valid.cityId.empty";
				
			}

			if(fieldName.equals("sex")) {
				if(value.trim().length() == 0) 
					result = "js.valid.sex.empty";
				else if("male".equals(value.trim()) ||  "female".equals(value.trim())) {
					
					result = "js.valid.ok";
				} else 
					result = "js.valid.sex.wrongformat";
			}


			if(fieldName.equals("cellNumber")) {
				if(value.trim().length() > MAX_LENGTH) 
					result = "js.valid.stringtoolong";
			}
			
		}
		
		
		return result;
	}
	

	private static String[] userFields = "email firstName lastName sex cellNumber password address cityId".split(" ");
	
	public static String[] getArrayOfFields(Class<?> clazz) {
		
		if(clazz == User.class) {
			return userFields;
		}
		
		return null;
	}
	
	public static boolean isPositiveNumber(String string) {
		if (string == null)
			return false;
		
		if(string.matches("^-?\\d+$")) {
			if(Integer.valueOf(string) > 0) 
				return true;
		}
		
		return false;
	}
	
	public static boolean isNumber(String string) {
		if (string == null)
			return false;
		
		if(string.matches("^-?\\d+$")) {
				return true;
		}
		
		return false;
	}	
}
