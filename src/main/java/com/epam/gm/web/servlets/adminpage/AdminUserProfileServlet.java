package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.services.CommentUserService;
import com.epam.gm.services.FriendUserService;
import com.epam.gm.services.UserTypeService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;


public class AdminUserProfileServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		FriendUserService friendUserService = new FriendUserService();
		UserDao userDao = new UserDao();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			request.setAttribute("commentUser", new CommentUserService().getByUserId(id));
			request.setAttribute("userActivity",userDao.userActivity(id));
			request.setAttribute("friends",friendUserService.getUserFriends(id));
			System.out.println("friends " + friendUserService.getUserFriends(id));
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		}
		request.setAttribute("user", userDao.getByField("id", id).get(0));
		request.setAttribute("userType", new UserTypeService().getAll());
		request.getRequestDispatcher("pages/adminUserDetail.jsp").forward(request,
				response);
	}
}