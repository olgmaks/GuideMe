package com.epam.gm.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.User;
import com.epam.gm.services.UserService;
import com.google.gson.Gson;

/**
 * Servlet implementation class AdminServletPost
 */
@WebServlet("/AdminServletPost")
public class AdminServletPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServletPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			if (action.equalsIgnoreCase("activeUser")){
				Integer userId = Integer.parseInt(request.getParameter("userId"));
				new UserDao().activeUser(userId);
			}else if(action.equalsIgnoreCase("filterByUserType")){
				PrintWriter out = response.getWriter();
				System.out.println(request.getParameter("userTypeId"));
				List<User> list = new UserService().getByUserType(request.getParameter("userTypeId"));
				String json = new Gson().toJson(list);
				  response.setContentType("application/json");
				  response.setCharacterEncoding("UTF-8");
				  out.write(json);
				  System.out.println(json);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}