package com.epam.gm.web.servlets.eventsincabitnet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.daolayer.EventDao;
import com.epam.gm.daolayer.UserInEventDao;
import com.epam.gm.model.Event;
import com.epam.gm.model.User;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class EventsInCabinetServlet extends HttpServlet implements
		HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2287493105910181661L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		HttpSession session = request.getSession();
		request.setAttribute("centralContent", "eventsincabinet");
		User user = (User) session.getAttribute("sessionUser");
		EventDao eventDao = new EventDao();
		UserInEventDao userInEventDao = new UserInEventDao();
	request.setAttribute("listOfUserInEvent", userInEventDao
				.getAllActualUserInEventWhereUserNotModeratorByUserId(user
						.getId()));
		request.setAttribute("listOfOldUserInEvent",
				userInEventDao
						.getAllOldUserInEventWhereUserNotModeratorByUserId(user
								.getId()));

		
		//gryn
		request.setAttribute("listOfOldModeratorEvents",
				eventDao.getOldAndNotDeletedEventsByModeratorId(user.getId()));
		

		
		
		request.setAttribute("listOfModeratorEvents", eventDao
				.getActiveAndNotDeletedEventsByModeratorId(user.getId()));

		request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(
				request, response);
	}
}
