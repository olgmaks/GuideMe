package com.epam.gm.web.servlets.userpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.FriendUserDao;
import com.epam.gm.model.FriendUser;
import com.epam.gm.model.User;
import com.epam.gm.services.FriendUserService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class EditFriendInUserProfileServlet implements HttpRequestHandler {

	private FriendUserService friendUserService = new FriendUserService();

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {

		System.out.println("HttpRequestHandler");

		try {
			Integer id = Integer.valueOf(request.getParameter("id"));
			String operation = request.getParameter("operation");
			Integer home = Integer.valueOf(request.getParameter("profile"));
			User user = SessionRepository.getSessionUser(request);
			
			if(user.getId() == null) {
				response.sendRedirect("401.do");
				return;
			}
			
			try {
			
			FriendUser friend = new FriendUserDao().getByField("id", id).get(0);
			
			if ("accept".equals(operation)) {

				if (friend.getFriendId().equals(user.getId())) {
					friendUserService.acceptFriendRequest(id);
					response.sendRedirect("userProfile.do?id="
							+ friend.getUserId().toString());
					
				}
				return;
			} else if ("discard".equals(operation)) {

				if (friend.getFriendId().equals(user.getId())) {
					friendUserService.declineFriendRequest(id);
					response.sendRedirect("userProfile.do?id="
							+ friend.getUserId().toString());
					
				}
				return;

			} else if ("callback".equals(operation)) {

				if (friend.getUserId().equals(user.getId())) {
					friendUserService.callBackFriendUserRequest(id);
					response.sendRedirect("userProfile.do?id="
							+ friend.getFriendId().toString());
					
				}
				return;
			} else if ("add".equals(operation)) {

				friendUserService.sendFriendRequest(user.getId(), id); 
					
				response.sendRedirect("userProfile.do?id="+id);
				return;
			} else if ("remove".equals(operation)) {

				if (friend.getUserId().equals(user.getId())) {
					friendUserService.removeFriend(id);
					response.sendRedirect("userProfile.do?id="
							+ friend.getFriendId().toString());
									}
				return;
			}
			
			} catch (Exception e1) {
				System.out.println("Go home friend!");
				response.sendRedirect("userProfile.do?id=" + home);
				return;
			}
			
			} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect("404.do");

	}
}
