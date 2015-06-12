package com.epam.gm.servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.UserType;
import com.epam.gm.services.UserTypeService;
import com.epam.gm.servlets.frontcontroller.HttpRequestHandler;

public class RegisterServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		UserTypeService userTypeService = new UserTypeService();
		
		List<UserType> userTypeList = userTypeService.getAll();
		
		request.setAttribute("userTypeList", userTypeList);
		
		request.getRequestDispatcher("register.jsp").forward(request, response);
		
	}

}
