package com.epam.gm.web.servlets.registerpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class ConfirmValidatorServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		System.out.println("ConfirmValidatorServlet");
		
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirm");
		
		System.out.println("password = " + password);
		System.out.println("confirm = " + confirm);
		
		String valid = "";
		if(password != null && confirm != null) {
			
			if(password.trim().length() > 0 && confirm.trim().length() > 0) {
				
				if(password.trim().equals(confirm.trim())) 
					valid = "js.valid.ok";
				else
					valid = "js.valid.confirmfail";
				
			} else if(password.trim().length() > 0 && confirm.trim().length() == 0) {
				valid = "js.valid.empty";
			}
			
		}
		
		System.out.println("valid = " + valid);
		response.setContentType("application/json");
		Map<String,Object> map = new HashMap<>();
		map.put("valid", valid);
		response.getWriter().write(new Gson().toJson(map));			
		
		
	}
	
	

}
