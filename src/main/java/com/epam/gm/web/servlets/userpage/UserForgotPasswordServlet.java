package com.epam.gm.web.servlets.userpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class UserForgotPasswordServlet extends HttpServlet implements
		HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8179960537469231049L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		
		request.getRequestDispatcher("pages/user/userforgotpasswordpage.jsp").forward(
				request, response);

	}

}
