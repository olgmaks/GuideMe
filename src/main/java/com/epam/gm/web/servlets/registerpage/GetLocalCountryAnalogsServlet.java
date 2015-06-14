package com.epam.gm.web.servlets.registerpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.Country;
import com.epam.gm.services.CountryService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.epam.gm.util.ValidateHelper;
import com.google.gson.Gson;

public class GetLocalCountryAnalogsServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {


		System.out.println("GetLocalCountryAnalogsServlet");
		
		String selectedValue = request.getParameter("value");
		System.out.println("value = " +selectedValue);
		
		Map<String, String> map = new HashMap<>();
//		map.put("#countryByLang_4", "6");
//		map.put("#countryByLang_3", "2");
		

		if(ValidateHelper.isNumber(selectedValue)) {
			//get our country
			CountryService countryService = new CountryService();
			Country country = countryService.getCountryById(Integer.parseInt(selectedValue));
			
			//get analogs
			List<Country> countries = countryService.getCountriesByPureId(country.getPureId());
			for(Country c: countries) {
				//map.put("#countryByLang_" + c.getLocalId(), c.getId().toString());
				
				//map.put("#cityByLang_" + c.getLocalId(), "choose");
				
				
				map.put(c.getLocalId().toString(), c.getId().toString());
			}
		}		
		
		
		String json = new Gson().toJson(map);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	
	}
	
}
