package com.epam.gm.web.servlets.eventsincabitnet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.daolayer.EventDao;

import com.epam.gm.model.User;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class ModeratorEventsServlet extends HttpServlet implements
		HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6402988608596415943L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("sessionUser");
		EventDao eventDao = new EventDao();

		request.setAttribute("listOfOldModeratorEvents",
				eventDao.getOldAndNotDeletedEventsByModeratorId(user.getId()));

		request.setAttribute("listOfModeratorEvents", eventDao
				.getActiveAndNotDeletedEventsByModeratorId(user.getId()));
		request.setAttribute("centralContent", "moderatorevent");
		request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(
				request, response);
	}
}
