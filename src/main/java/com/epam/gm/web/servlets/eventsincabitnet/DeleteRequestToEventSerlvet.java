package com.epam.gm.web.servlets.eventsincabitnet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.UserInEventDao;
import com.epam.gm.model.User;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class DeleteRequestToEventSerlvet extends HttpServlet implements
		HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6245955580463344597L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		Integer requestId = Integer.parseInt(request
				.getParameter("requestidval"));
		User user = (User) request.getSession().getAttribute("sessionUser");
		System.out.println(requestId);
		new UserInEventDao().deleteUserFromEvent(requestId, user.getId());
	}
}
