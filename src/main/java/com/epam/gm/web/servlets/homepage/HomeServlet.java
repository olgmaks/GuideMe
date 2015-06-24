package com.epam.gm.web.servlets.homepage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.calculators.EventCalculator;
import com.epam.gm.model.Country;
import com.epam.gm.model.Event;
import com.epam.gm.model.Language;
import com.epam.gm.model.User;
import com.epam.gm.services.CountryService;
import com.epam.gm.services.EventService;
import com.epam.gm.services.LanguageService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.util.Constants;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class HomeServlet extends HttpServlet implements HttpRequestHandler {
	private static final long serialVersionUID = 1L;

	private EventService eventService = new EventService();

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		User user = SessionRepository.getSessionUser(request);
		Integer userId = null;
		Integer cityId = null;
		if (user != null) {
			userId = user.getId();
			if (user.getAddress() != null
					&& user.getAddress().getCity() != null)
				cityId = user.getAddress().getCity().getId();

		}

		// gryn - top user's events

		try {
			// user events
			List<Event> topUserEvents = eventService
					.getAllActiveNotDeletedUserEvents();
			EventCalculator.sortEventsByPoints(topUserEvents, userId);

			if (topUserEvents.size() > Constants.TOP_NUMBER) {
				topUserEvents = new ArrayList<Event>(topUserEvents.subList(0,
						Constants.TOP_NUMBER));
			}
			request.setAttribute("topUserEvents", topUserEvents);

			// guide events
			List<Event> topGuideEvents = null;
			if (cityId == null)
				topGuideEvents = eventService
						.getAllActiveNotDeletedGuideEvents();
			else
				topGuideEvents = eventService
						.getAllActiveNotDeletedGuideEventsInTheCity(cityId);

			EventCalculator.sortEventsByPoints(topGuideEvents, userId);

			if (topGuideEvents.size() > Constants.TOP_NUMBER) {
				topGuideEvents = new ArrayList<Event>(topGuideEvents.subList(0,
						Constants.TOP_NUMBER));
			}
			request.setAttribute("topGuideEvents", topGuideEvents);

			LanguageService languageService = new LanguageService();
			List<Language> languageList = languageService
					.getUserLangsForLocal(user); // languageService.getLocalizedLangs();
			request.setAttribute("languageList", languageList);

			CountryService countryService = new CountryService();
			List<Country> countryList = countryService.getAll();
			request.setAttribute("countryList", countryList);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("pages/index.jsp");
		requestDispatcher.forward(request, response);

	}

}
