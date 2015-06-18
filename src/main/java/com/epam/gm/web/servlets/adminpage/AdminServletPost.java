package com.epam.gm.web.servlets.adminpage;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.User;
import com.epam.gm.services.UserService;
import com.epam.gm.utf8uncoder.StringHelper;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class AdminServletPost
 */

public class AdminServletPost implements HttpRequestHandler{


    /**
     * @see HttpServlet#HttpServlet()
     */

    @Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
        String action = request.getParameter("action");
        request.setCharacterEncoding("UTF-8");
        System.out.println(action);
        try {
            if (action.equalsIgnoreCase("activeUser")) {
                Integer userId = Integer.parseInt(request
                        .getParameter("userId"));
                new UserDao().activeUser(userId);
            } else if (action.equalsIgnoreCase("filterByUserType")) {
                PrintWriter out = response.getWriter();
                List<User> list = null;
                String userTypeId = request.getParameter("userTypeId");
                if (!userTypeId.equals("0")) {
                    list = new UserService().getByUserType(userTypeId);
                } else {
                    list = new UserService().getAll();
                }
                String json = new Gson().toJson(list);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.write(json);
            } else if (action.equalsIgnoreCase("delete")) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                new UserService().deleteById(userId);
                List<User> list = null;
                String userTypeId = request.getParameter("userTypeId");
                if (!userTypeId.equals("0")) {
                    list = new UserService().getByUserType(userTypeId);
                } else {
                    list = new UserService().getAll();
                }
                PrintWriter out = response.getWriter();
                String json = new Gson().toJson(list);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.write(json);
            } else if (action.equalsIgnoreCase("edit")) {
            	Map<String, Object> map = new HashMap<>();
            	Integer userId = Integer.parseInt(request.getParameter("userId"));
                map.put("last_name",StringHelper.convertFromUTF8(request.getParameter("lastName")));
                map.put("first_name", StringHelper.convertFromUTF8(request.getParameter("firstName")));
                map.put("sex", request.getParameter("sex"));
                map.put("user_type_id", request.getParameter("userTypeId"));
                UserDao userDao = new UserDao();
                userDao.updateById(userId, map);
                response.sendRedirect("adminUserProfile.do?id=" + userId);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}