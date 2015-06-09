package com.epam.gm.hashpassword;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashPassword {
	 public static String getHashPassword(String password) throws NoSuchAlgorithmException{		  
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(password.getBytes());
	 
	        byte byteData[] = md.digest();
	 
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	 
	       return sb.toString();
	 
	  }
}
