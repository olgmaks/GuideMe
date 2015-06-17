package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.services.UserService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;


public class AdminUserProfileServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		UserDao userDao = new UserDao();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			request.setAttribute("userActivity",userDao.userActivity(id));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("user", userDao.getByField("id", id).get(0));
		request.getRequestDispatcher("pages/adminUserDetail.jsp").forward(request,
				response);
	}
	public static void main(String[] args) {
		try {
			System.out.println(new UserDao().userActivity(2));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}