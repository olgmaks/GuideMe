package com.epam.gm.web.servlets.local;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.Language;
import com.epam.gm.services.LanguageService;
import com.epam.gm.services.UserService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.util.CookieUtil;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class ChangeLocalServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {

		System.out.println("ChangeLocalServlet");
		
		String lang = request.getParameter("lang");
		String from = request.getParameter("from");
		
		System.out.println("lang = " + lang);
		System.out.println("from = " + from);
		
		if(lang == null || from == null) {
			response.sendRedirect("404.do");
			return;
		}
		
		Language language = new LanguageService().getLangByKey(lang);
		
		System.out.println("Setting lang : " + language);
		
		SessionRepository.setSessionLanguage(request, language);
		
		System.out.println("save to user");
		if(SessionRepository.getSessionUser(request) != null && language != null) {
			UserService us = new UserService();
			
			System.out.println("save lang to db  " + language.getId());
			us.saveUserLang(SessionRepository.getSessionUser(request).getId(), language.getId());
			
			//and cookie
			CookieUtil.saveLastLanguage(response, language);
		}
		
		
		if(from.trim().length() == 0) 
			response.sendRedirect("home.do");
		else
			response.sendRedirect(from);
		
	}

}
