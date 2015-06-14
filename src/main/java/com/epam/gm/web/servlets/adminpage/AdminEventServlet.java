package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.epam.gm.services.EventService;

import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AdminEventServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		EventService eventService = new EventService();
	System.out.println(eventService.getAll());
		request.setAttribute("eventList", eventService.getAll());
		request.getRequestDispatcher("pages/adminEvent.jsp").forward(request, response);
		
	}
	

}

