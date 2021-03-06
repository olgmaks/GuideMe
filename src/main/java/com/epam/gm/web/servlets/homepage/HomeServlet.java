package com.epam.gm.web.servlets.homepage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.calculators.EventCalculator;
import com.epam.gm.calculators.UserCalculator;
import com.epam.gm.model.Country;
import com.epam.gm.model.Event;
import com.epam.gm.model.Language;
import com.epam.gm.model.User;
import com.epam.gm.services.CountryService;
import com.epam.gm.services.EventService;
import com.epam.gm.services.LanguageService;
import com.epam.gm.services.UserService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.util.Constants;
import com.epam.gm.util.CookieUtil;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class HomeServlet extends HttpServlet implements HttpRequestHandler {
	private static final long serialVersionUID = 1L;

	private EventService eventService = new EventService();
	private UserService userService = new UserService();
	private CountryService countryService = new CountryService();

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		// java.net.URL url =
		// getClass().getClassLoader().getResource("locale/home");
		// System.out.println("Path: " + url.getPath());

		SessionRepository.initBundle(request, "locale.home.messages");

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
			Integer lastCountryId = CookieUtil.getLastCountryId(request);
			System.out
					.println("*************------------------*******************lastCountryId = "
							+ lastCountryId);
			System.out.println("************* user = " + user);

			// user events
			List<Event> topUserEvents = eventService
					.getAllNotDeletedEventsInTheCountry(lastCountryId);
			EventCalculator.sortEventsByPoints(topUserEvents, userId);

			if (topUserEvents.size() > Constants.TOP_NUMBER) {
				topUserEvents = new ArrayList<Event>(topUserEvents.subList(0,
						Constants.TOP_NUMBER));
			}
			eventService.buildTagString(topUserEvents);
			request.setAttribute("topUserEvents", topUserEvents);

			List<Event> lastEvents = null;

			String byTag = request.getParameter("tag");
			if (byTag == null || byTag.trim().length() == 0) {
				lastEvents = new ArrayList<Event>(topUserEvents);
				Collections.sort(lastEvents, Event.BY_CREATED_DATE);

				Country country = countryService.getCountryById(lastCountryId);
				request.setAttribute(
						"searchEventTitle",
						SessionRepository
								.getLocaleMessage("index.LatestEventsIn")
								+ ": " + country.getName());

				for (Event e : lastEvents) {
					System.out.println("--------------------------");
					System.out.println(e.getStatus() == null);
					System.out.println(e.getModerator().getUserType());
				}

			} else {
				lastEvents = eventService.getByTagName(byTag.trim());
				eventService.buildTagString(lastEvents);
				EventCalculator.sortEventsByPoints(lastEvents, userId);

				request.setAttribute("searchEventTitle",
						SessionRepository.getLocaleMessage("index.AllActiveBy")
								+ ": #" + byTag);
			}

			request.setAttribute("lastEvents", lastEvents);

			List<User> topUsers = userService
					.getActiveUsersAndGuidesInTheCountry(lastCountryId);
			UserCalculator.sortUsersByPoints(topUsers, userId);
			if (topUsers.size() > Constants.TOP_NUMBER) {
				topUsers = new ArrayList<User>(topUsers.subList(0,
						Constants.TOP_NUMBER));
			}
			request.setAttribute("topUsers", topUsers);

			LanguageService languageService = new LanguageService();
			
			List<Language> languageList = null;
			
			if(user == null) {
				Language sessionLang = SessionRepository.getSessionLanguage(request);
				if(sessionLang != null) {
					languageList  = new ArrayList<Language>();
					languageList.add(sessionLang);
				}
					
			}
			
			if(languageList == null || languageList.isEmpty())
				languageList = languageService
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
