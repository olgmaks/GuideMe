package com.epam.gm.web.servlets.usertags;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class UserTagsServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		System.out.println("UserTagsServlet");
		
	}

}