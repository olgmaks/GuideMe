package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.EventDao;
import com.epam.gm.model.User;
import com.epam.gm.services.UserService;
import com.epam.gm.services.UserTypeService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AdminServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		User user = new User();
		user = SessionRepository.getSessionUser(request);

		if (user == null) {
			response.sendRedirect("401.do");
			return;
		}
		request.setAttribute("isAdmin", SessionRepository.isAdmin(request));
		UserService userService = new UserService();
		List<User> userList = userService.getAll();
		request.setAttribute("eventList", new EventDao().getAllEvents());
		request.setAttribute("userList", userList);
		request.setAttribute("userType", new UserTypeService().getAll());
		//request.getRequestDispatcher("pages/admin.jsp").forward(request, response);
		 request.setAttribute("centralContent", "adminEvent");
		request.getRequestDispatcher("pages/admin/adminPanel.jsp").forward(request, response);
	}
	

}
