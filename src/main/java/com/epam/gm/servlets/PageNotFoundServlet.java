package com.epam.gm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.servlets.frontcontroller.HttpRequestHandler;

public class PageNotFoundServlet extends HttpServlet implements HttpRequestHandler {
	private static final long serialVersionUID = 1L;
       
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Error 404");
	}

}
