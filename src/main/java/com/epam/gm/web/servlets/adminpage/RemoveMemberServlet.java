package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.services.UserInEventService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class RemoveMemberServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {

		try {

			System.out.println("RemoveMemberServlet");
			Integer userId = Integer.valueOf(request
					.getParameter("userFriendId"));
			System.out.println("userId :  " + userId);

			Integer eventId = Integer.valueOf(request
					.getParameter("memberEventId"));

			System.out.println("eventId :" + eventId);
			
			new UserInEventService().deleteUserFromEvent(eventId, userId);
			

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("404.do");
		}
	}

}
