package com.epam.gm.web.servlets.homepage;

import com.epam.gm.model.User;
import com.epam.gm.services.UserService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.epam.gm.sessionrepository.SessionRepository;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet implements HttpRequestHandler {

    private static final long serialVersionUID = 1L;
    private UserService userService;

    public LoginServlet() {
        userService = new UserService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.getUserByEmail(email);

        System.out.println(email);
        System.out.println(password);
        System.out.println("ajax query has been parsed !");

        Map<String, Object> map = new HashMap<>();
        boolean isValid = false;
        
        if (user != null) {
            if (user.getPassword().equals(password)) {
                System.out.println("logination has been successful");
                SessionRepository.setSessionUser(request, user);
                isValid = true;
                map.put("userEmail", user.getEmail());
                map.put("sessionUser", user);
            } else {
                isValid = false;
            }
        }
        response.setContentType("application/json");
        map.put("isValid", isValid);
        response.getWriter().write(new Gson().toJson(map));

//	RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
//	requestDispatcher.forward(request, response);

    }

}
