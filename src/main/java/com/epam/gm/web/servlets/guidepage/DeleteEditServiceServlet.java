package com.epam.gm.web.servlets.guidepage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.ServiceDao;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class DeleteEditServiceServlet extends HttpServlet implements
		HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4102193107274275929L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		String act = request.getParameter("act");
		ServiceDao serviceDao = new ServiceDao();

		Integer id = null;
		if (act.equals("delete")) {
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id"));
				serviceDao.deleteServiceById(id);
			}
		}
		System.out.println(act + "\n" + id);
		response.sendRedirect(response.encodeRedirectURL("guideservices.do"));

	}
}
