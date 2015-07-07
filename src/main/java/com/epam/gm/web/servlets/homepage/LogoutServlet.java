package com.epam.gm.web.servlets.homepage;

import com.epam.gm.model.Language;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

public class LogoutServlet extends HttpServlet implements HttpRequestHandler {

	/**
     *
     */
	private static final long serialVersionUID = 1L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		//gryn
		//get lang from last session and setting to new 
		HttpSession session = request.getSession(false);
		Language lang = null;
		if(session != null) {
			lang = SessionRepository.getSessionLanguage(request);
		}
		
		request.getSession(true).invalidate();
		
		if(lang != null) {
			SessionRepository.setSessionLanguage(request, lang);
		}

		response.sendRedirect(response.encodeRedirectURL(request
				.getContextPath() + "/home.do"));
	}
}
