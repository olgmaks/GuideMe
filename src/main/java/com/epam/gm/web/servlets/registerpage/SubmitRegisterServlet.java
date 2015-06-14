package com.epam.gm.web.servlets.registerpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.User;
import com.epam.gm.util.ValidateHelper;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class SubmitRegisterServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		System.out.println("SubmitRegisterServlet");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        System.out.println(email);
//        System.out.println(password);	
		
		boolean ok = true;
		
		Map<String, String[]> params = request.getParameterMap();
		for(Map.Entry<String, String[]> entry : params.entrySet() ) { 
			if (entry.getKey() == null) continue;
			
//			System.out.println("key = " + entry.getKey());
//			System.out.println("value = " +  Arrays.toString(entry.getValue())  );
			
			if(entry.getKey().startsWith("addressByLang_")) {
				
				String key = entry.getKey().replace("addressByLang_", "").trim();
				if(ValidateHelper.isNumber(key)) {
					Integer langId = Integer.parseInt(key);
					
					if(entry.getValue() != null && entry.getValue().length > 0) {
						String value = entry.getValue()[0];
						
						String result = ValidateHelper.validateField("address", value, User.class);
						
						System.out.println("**********************");
						System.out.println("param = " + entry.getKey());
						System.out.println("value = " + value);
						System.out.println("result = " + result);
						
						if(!"".equals(result))
							if(!result.endsWith(".ok"))
								ok = false;						
					}
					
				}
				
				
			} else {
				
				String param = entry.getKey().trim();
				String value = "";
				if(entry.getValue() != null && entry.getValue().length > 0) 
					value = entry.getValue()[0];
				
				String result = ValidateHelper.validateField(param, value, User.class);
				
				System.out.println("**********************");
				System.out.println("param = " + param);
				System.out.println("value = " + value);
				System.out.println("result = " + result);
				
				if(!"".equals(result))
					if(!result.endsWith(".ok"))
						ok = false;
				
			}
		}	
		
		System.out.println("ok="+ok);
		
	}
	
}
