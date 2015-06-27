package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.Event;
import com.epam.gm.model.User;
import com.epam.gm.services.EventService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.util.ValidateHelper;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class JoinEventServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		
		System.out.println("JoinEventServlet");
		
		String id = request.getParameter("id");
		String operation = request.getParameter("operation");
		if(id != null && ("join".equals(operation) || "quit".equals(operation)) && ValidateHelper.isNumber(id)) {
			EventService eventService = new EventService();
			Event event = eventService.getById(Integer.parseInt(id.trim()));
			
			if(event == null) {
				response.sendRedirect("404.do");
				return;
			}
			
			User user = new User();
			user = SessionRepository.getSessionUser(request);			
			
			if(user == null) {
				response.sendRedirect("401.do");
				return;
			}
			
			if("quit".equals(operation)) {
				
			}
			
			return;
		}
		
		response.sendRedirect("404.do");
		
	}
	
}
