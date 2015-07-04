package com.epam.gm.web.servlets.guidepage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.daolayer.ServiceDao;
import com.epam.gm.model.User;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class GuideServicesServlet extends HttpServlet implements
		HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8099172618893108510L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("sessionUser");
		request.setAttribute("centralContent", "guideservices");
		request.setAttribute("guideservices",
				new ServiceDao().getNotTemporaryServicesByGuideId(u.getId()));
		request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(
				request, response);

	}
}
