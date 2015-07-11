package com.epam.gm.web.servlets.guidepage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.daolayer.ServiceDao;
import com.epam.gm.model.Service;
import com.epam.gm.model.User;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AddServiceServlet extends HttpServlet implements
		HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4466315073315741101L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Double price = Double.parseDouble(request.getParameter("price"));
		Service s = new Service();
		User u = (User) session.getAttribute("sessionUser");
		s.setGuideId(u.getId());
		s.setName(name);
		s.setDescription(description);
		s.setPrice(round(price, 1));
		ServiceDao serviceDao = new ServiceDao();
		serviceDao.addService(s);
		response.sendRedirect(response.encodeRedirectURL("guideservices.do"));
	}

	private static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
