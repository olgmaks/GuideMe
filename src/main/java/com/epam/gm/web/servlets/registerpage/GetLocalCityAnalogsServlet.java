package com.epam.gm.web.servlets.registerpage;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.epam.gm.model.City;
import com.epam.gm.services.CityService;
import com.epam.gm.util.ValidateHelper;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class GetLocalCityAnalogsServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		
		System.out.println("GetLocalCityAnalogsServlet");
		
		String selectedValue = request.getParameter("value");
		System.out.println("value = " +selectedValue);
		
		Map<String, String> map = new HashMap<>();

		if(ValidateHelper.isNumber(selectedValue)) {
			//get our city
			CityService cityService = new CityService();
			City city = cityService.getCityById(Integer.parseInt(selectedValue));
					
			
			//get analogs
			List<City> cities = cityService.getCitiesByPureId(city.getPureId());
			for(City c: cities) {
				map.put(c.getLocalId().toString(), c.getId().toString());
			}
		}		
		
		String json = new Gson().toJson(map);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);		
		
	}
	
}


