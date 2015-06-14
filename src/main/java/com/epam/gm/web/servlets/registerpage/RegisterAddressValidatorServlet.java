package com.epam.gm.web.servlets.registerpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.User;
import com.epam.gm.util.ValidateHelper;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class RegisterAddressValidatorServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		System.out.println("RegisterAddressValidatorServlet ---");
		
		//String value = request.getParameter("addressByLang_4");
		//System.out.println("val="+value);
		
		Map<String, String[]> params = request.getParameterMap();
		for(Map.Entry<String, String[]> entry : params.entrySet() ) { 
			System.out.println("key = " + entry.getKey());
			System.out.println("value = " +  Arrays.toString(entry.getValue())  );
			
			if(entry.getKey() != null) {
				
				if(entry.getKey().startsWith("addressByLang_")) {
					String key = entry.getKey().replace("addressByLang_", "").trim();
					if(ValidateHelper.isNumber(key)) {
						Integer langId = Integer.parseInt(key);
						
						if(entry.getValue() != null && entry.getValue().length > 0) {
							String value = entry.getValue()[0];
							
							String valid = ValidateHelper.validateField("address", value, User.class);
							
							System.out.println("valid = " + valid);
							
							response.setContentType("application/json");
							Map<String,Object> map = new HashMap<>();
							map.put("valid", valid);
							
							response.getWriter().write(new Gson().toJson(map));	
							
							
						}
						
					}
				}
				
			}
		}
		
		
		
		
	}

}
