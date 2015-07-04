package com.epam.gm.web.servlets.eventsincabitnet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.daolayer.EventDao;
import com.epam.gm.daolayer.UserInEventDao;
import com.epam.gm.model.User;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class MemberEventsServlet extends HttpServlet implements
		HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3127708296209217802L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("sessionUser");
		UserInEventDao userInEventDao = new UserInEventDao();
		request.setAttribute("listOfUserInEvent", userInEventDao
				.getAllActualUserInEventWhereUserNotModeratorByUserId(user
						.getId()));
		request.setAttribute("listOfOldUserInEvent",
				userInEventDao
						.getAllOldUserInEventWhereUserNotModeratorByUserId(user
								.getId()));
		request.setAttribute("centralContent", "memberevent");
		request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(
				request, response);

	}
}
