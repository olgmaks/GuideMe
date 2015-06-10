package com.epam.gm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.services.UserService;
import com.epam.gm.servlets.frontcontroller.HttpRequestHandler;


public class LoginServlet extends HttpServlet implements HttpRequestHandler {
    
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    
	    
	    
		response.getWriter().append("Served at: ").append(request.getContextPath()).append("login page");
		
	}

}
