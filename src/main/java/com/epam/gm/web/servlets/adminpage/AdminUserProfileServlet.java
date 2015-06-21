package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.RatingUserDao;
import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.User;
import com.epam.gm.services.CommentUserService;
import com.epam.gm.services.FriendUserService;
import com.epam.gm.services.UserTypeService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;


public class AdminUserProfileServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		FriendUserService friendUserService = new FriendUserService();
		User user = new User();
		RatingUserDao ruDao = new RatingUserDao();
		user = SessionRepository.getSessionUser(request);
		UserDao userDao = new UserDao();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			request.setAttribute("userLogined", SessionRepository.getSessionUser(request));
			request.setAttribute("commentUser", new CommentUserService().getByUserId(id));
			request.setAttribute("userActivity",userDao.userActivity(id));
			request.setAttribute("friends",friendUserService.getUserFriends(id));
			Integer mark = null;
			if (user != null){
				if (ruDao.getMarkByEvent(id, user.getId()) != null){
					mark =  ruDao.getMarkByEvent(id, user.getId()).getMark();
				}
			}
			request.setAttribute("mark", mark);
			System.out.println("friends " + friendUserService.getUserFriends(id));
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		}
		request.setAttribute("user", userDao.getByField("id", id).get(0));
		request.setAttribute("userType", new UserTypeService().getAll());
		request.setAttribute("centralContent", "adminUserDetail");
			request.getRequestDispatcher("pages/admin/adminPanel.jsp").forward(request, response);
	}
}