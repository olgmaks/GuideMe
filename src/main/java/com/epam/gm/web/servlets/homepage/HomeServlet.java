package com.epam.gm.web.servlets.homepage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.calculators.EventCalculator;
import com.epam.gm.model.Event;
import com.epam.gm.model.User;
import com.epam.gm.services.EventService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.util.Constants;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class HomeServlet extends HttpServlet implements HttpRequestHandler {
	private static final long serialVersionUID = 1L;

	private EventService eventService = new EventService();
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		User user =  SessionRepository.getSessionUser(request);
		Integer userId = null;
		if(user != null) {
			userId = user.getId();
		}
		
		//gryn - top user's events
		
		try {
			List<Event> topUserEvents = eventService.getAllActiveNotDeletedEvents();
			EventCalculator.sortEventsByPoints(topUserEvents, userId);
			
			request.setAttribute("topUserEvents", topUserEvents);
			request.setAttribute("topSize", Integer.min(topUserEvents.size(), Constants.TOP_NUMBER));
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("pages/index.jsp");
		requestDispatcher.forward(request, response);

	}

}
