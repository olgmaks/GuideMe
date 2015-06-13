package com.epam.gm.servlets;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.*;
import com.epam.gm.services.LanguageService;

import com.epam.gm.servlets.frontcontroller.HttpRequestHandler;

public class RegisterServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		
		LanguageService languageService = new LanguageService();
		
		List<Language> languageList = languageService.getLocalizedLangs();
		
		request.setAttribute("languageList", languageList);
		
		request.getRequestDispatcher("register.jsp").forward(request, response);
		
	}

}
