package com.epam.gm.web.servlets.registerpage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.User;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.epam.gm.util.ValidateHelper;
import com.google.gson.Gson;

public class RegisterValidatorServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("RegisterValidatorServlet");
		
		String[] params = ValidateHelper.getArrayOfFields(User.class);
		for(String p: params) {
			String value = request.getParameter(p);

			
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!! param = " + p);
			
			if(value == null) continue;

			
			
			
			String valid = ValidateHelper.validateField(p, value, User.class);
			
//			if(valid.contains(".ok"))
//				valid = "<b>" + valid + "</b>";
			
			
			System.out.println("valid = " + valid);
			
			response.setContentType("application/json");
			
			Map<String,Object> map = new HashMap<>();
			map.put("valid", valid);
			
			response.getWriter().write(new Gson().toJson(map));
		}
		
	}

}
