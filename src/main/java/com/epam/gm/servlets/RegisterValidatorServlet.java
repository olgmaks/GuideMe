package com.epam.gm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.servlets.frontcontroller.HttpRequestHandler;

public class RegisterValidatorServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("RegisterValidatorServlet");
		
	}

}
