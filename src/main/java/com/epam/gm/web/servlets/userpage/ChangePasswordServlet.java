package com.epam.gm.web.servlets.userpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.ForgotPasswordDao;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class ChangePasswordServlet extends HttpServlet implements
		HttpRequestHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5854834661058077682L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		String code = request.getParameter("ctcp");
		System.out.println(code);
		ForgotPasswordDao forgotPasswordDao = new ForgotPasswordDao();
		if (forgotPasswordDao.isAvailableCode(code)) {
			request.getRequestDispatcher("pages/user/changepasswordpage.jsp")
					.forward(request, response);
		} else {
			System.out.println("not available");
		}
	}
}
