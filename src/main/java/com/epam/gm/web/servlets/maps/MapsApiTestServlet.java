package com.epam.gm.web.servlets.maps;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class MapsApiTestServlet implements HttpRequestHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
			SQLException, IllegalAccessException {
		
		request.getRequestDispatcher("pages/maps/map-simple.jsp").forward(request, response);
		
	}

}
