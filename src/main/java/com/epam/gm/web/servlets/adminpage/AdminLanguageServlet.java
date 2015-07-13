package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.User;
import com.epam.gm.services.LanguageService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AdminLanguageServlet implements HttpRequestHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		
		User user = new User();
		user = SessionRepository.getSessionUser(request);

		if (user == null) {
			response.sendRedirect("401.do");
			return;
		}
		request.setAttribute("isAdmin", SessionRepository.isAdmin(request));
	    LanguageService ls = new LanguageService();
	    
		request.setAttribute("languageList", ls.getAllActiveLangs());
		request.setAttribute("centralContent", "adminLanguage");
		request.getRequestDispatcher("pages/admin/adminPanel.jsp").forward(request,
				response);
		
	}
	  
}
