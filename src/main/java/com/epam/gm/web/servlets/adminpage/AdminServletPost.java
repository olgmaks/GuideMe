package com.epam.gm.web.servlets.adminpage;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.User;
import com.epam.gm.services.UserService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

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
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
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
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}