package com.epam.gm.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.servlets.frontcontroller.HttpRequestHandler;

public class GetCitiesByCountryServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		System.out.println("GetCitiesByCountryServlet");
		

		String selectedValue = request.getParameter("value");
		String otherCity = request.getParameter("other");

		
		System.out.println("value = " +selectedValue);
		System.out.println("other = " +otherCity);
		
		
//		CityDAO city = new CityDAO();
//		Map<Integer, String> options = city.getByCountry(selectedValue,
//				otherCity);
//		String json = new Gson().toJson(options);
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().write(json);
		
	}
	
}
