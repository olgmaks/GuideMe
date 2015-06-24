package com.epam.gm.web.servlets.homepage;

import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LogoutServlet extends HttpServlet implements HttpRequestHandler {

	/**
     *
     */
	private static final long serialVersionUID = 1L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		request.getSession(true).invalidate();

		response.sendRedirect(response.encodeRedirectURL(request
				.getContextPath() + "/home.do"));
	}
}
