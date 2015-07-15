package com.epam.gm.web.servlets.registerpage.event;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.Event;
import com.epam.gm.model.User;
import com.epam.gm.util.ValidateHelper;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class RegisterValidatorEventServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		
		System.out.println("RegisterValidatorEventServlet");
		
		String[] params = ValidateHelper.getArrayOfFields(Event.class);
		for(String p: params) {
			String value = request.getParameter(p);
			
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!! param = " + p);
			
			if(value == null) continue;
			
			String valid = ValidateHelper.validateField(p, value, Event.class);
			

			System.out.println("valid = " + valid);
			
			response.setContentType("application/json");
			
			Map<String,Object> map = new HashMap<>();
			map.put("valid", valid);
			
			response.getWriter().write(new Gson().toJson(map));
		}		
		
	}

}
