package com.epam.gm.web.servlets.registerpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.City;
import com.epam.gm.services.CityService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class GetCitiesByCountryServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		System.out.println("GetCitiesByCountryServlet");


		/**will be executed with ajax query
		 *
		 */
		if (request.getParameter("cityRequestType").equals("getAllCity")){
			CityService cityService = new CityService();

			List<String> cities = new ArrayList<>();
			for (City city : cityService.getCitiesByEnglishLocal()) {
				cities.add(city.getName());
			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			System.out.println(cities);
			response.getWriter().write(new Gson().toJson(cities));
			return;
		}

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
