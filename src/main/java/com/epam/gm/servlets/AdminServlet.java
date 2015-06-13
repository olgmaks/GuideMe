package com.epam.gm.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.User;
import com.epam.gm.servlets.frontcontroller.HttpRequestHandler;

public class AdminServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		
		UserDao userdao = new UserDao();
		
		List<User> userList = userdao.getAll();
		
		request.setAttribute("userList", userList);
		
		request.getRequestDispatcher("admin.jsp").forward(request, response);
		
	}
	

}
