package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.User;
import com.epam.gm.services.UserService;
import com.epam.gm.services.UserTypeService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AdminUserServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			User user = new User();
			user = SessionRepository.getSessionUser(request);

			if (user == null) {
				response.sendRedirect("401.do");
				return;
			}

			UserService userService = new UserService();
			List<User> userList = userService.getAll();
			request.setAttribute("userList", userList);
			request.setAttribute("userType", new UserTypeService().getAll());
			request.setAttribute("centralContent", "adminUser");
			request.setAttribute("isAdmin", SessionRepository.isAdmin(request));
			request.getRequestDispatcher("pages/admin/adminPanel.jsp").forward(
					request, response);
		} catch (ServletException | IOException | SQLException e) {

			response.sendRedirect("404.do");
		}

	}

}
