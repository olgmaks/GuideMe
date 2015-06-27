package com.epam.gm.web.servlets.eventsincabitnet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class ModeratorEventsServlet extends HttpServlet implements HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6402988608596415943L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {

		
		request.setAttribute("centralContent", "moderatorevent");
		request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(
				request, response);
	}
}
