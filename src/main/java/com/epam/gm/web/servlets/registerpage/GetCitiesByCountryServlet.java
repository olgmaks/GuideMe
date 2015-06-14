package com.epam.gm.web.servlets.registerpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.services.CityService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class GetCitiesByCountryServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		System.out.println("GetCitiesByCountryServlet");
		
		String selectedValue = request.getParameter("value");

		
		System.out.println("value = " +selectedValue);
		
		CityService cityService = new CityService();
		Map<Integer, String> options = cityService.getMapOfCitiesByCountryId(Integer.parseInt(selectedValue));
		String json = new Gson().toJson(options);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}
	
}
