package com.epam.gm.web.servlets.eventpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.CountryDao;
import com.epam.gm.model.*;
import com.epam.gm.services.CityService;
import com.epam.gm.services.CountryService;
import com.epam.gm.services.LanguageService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AddEventServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		CountryService countryService = new CountryService();
		CityService cityService = new CityService();
		
		List<Country> countryList = countryService.getAll();
		request.setAttribute("countryList", countryList);
		
		int country_id=0;
		
		List<City> cityList = cityService.getCitiesByCountryId(country_id);
		request.setAttribute("cityList", cityList);

		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("pages/addEvent.jsp");
		requestDispatcher.forward(request, response);

	}

}
