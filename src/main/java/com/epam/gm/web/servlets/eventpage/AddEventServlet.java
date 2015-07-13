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
import com.epam.gm.services.LanguageService;
import com.epam.gm.services.WalletService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AddEventServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("sessionUser");
		Wallet wallet = new Wallet();
		WalletService walletService = new WalletService();
		request.setAttribute("centralContent", "addEvent");
		
		LanguageService languageService = new LanguageService();
		List<Language> languageList = languageService.getLocalizedLangs();
		request.setAttribute("languageList", languageList);
		
		
		CountryService countryService = new CountryService();
		List<Country> countryList = countryService.getAll();
		request.setAttribute("countryList", countryList);

		
		request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(
				request, response);
		

	}

}
