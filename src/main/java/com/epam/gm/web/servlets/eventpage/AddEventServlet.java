package com.epam.gm.web.servlets.eventpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.model.*;
import com.epam.gm.services.CountryService;
import com.epam.gm.services.EventService;
import com.epam.gm.services.LanguageService;
import com.epam.gm.services.WalletService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.util.Constants;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AddEventServlet implements HttpRequestHandler {

	private EventService eventService;

	public AddEventServlet () {
		eventService = new EventService();
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("sessionUser");
		User user = SessionRepository.getSessionUser(request);

		Wallet wallet = new Wallet();
		WalletService walletService = new WalletService();
		request.setAttribute("centralContent", "addEvent");
		
		LanguageService languageService = new LanguageService();
		List<Language> languageList = languageService.getLocalizedLangs();
		request.setAttribute("languageList", languageList);


		List<Event> recommendedEvents =  eventService.getUserFriendEvents(user.getId());
		request.setAttribute("recommendedEvents",recommendedEvents);
		
		CountryService countryService = new CountryService();
		List<Country> countryList = countryService.getAll();
		request.setAttribute("countryList", countryList);
		
		Double lattitude = null;
		Double longitude = null;
		
		if(user != null && user.getAddress() != null) {
			if(user.getAddress().getLattitude() != null) {
				lattitude = user.getAddress().getLattitude();
				longitude = user.getAddress().getLongitude();
			} else {
				if(user.getAddress().getCity() != null ) {
					lattitude = user.getAddress().getCity().getLattitude();
					longitude = user.getAddress().getCity().getLongitude();
				}
			}
		}
		request.setAttribute("lattitude", lattitude);
		request.setAttribute("longitude", longitude);
		request.setAttribute("mapsKey", Constants.MAPS_KEY);
		
		System.out.println("lattitude = " + lattitude);
		System.out.println("longitude = " + longitude);
		
		
		request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(
				request, response);
		

	}

}
