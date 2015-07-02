package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.epam.gm.daolayer.CountryDao;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AdminCountryServlet implements HttpRequestHandler{
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		request.setAttribute("centralContent", "adminCountry");
		request.setAttribute("countryList",new CountryDao().getAll());
		request.getRequestDispatcher("pages/admin/adminPanel.jsp").forward(request,
				response);
	}
}
