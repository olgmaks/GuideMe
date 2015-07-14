package com.epam.gm.web.servlets.homepage;

import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by OLEG on 14.07.2015.
 */
public class LoginPageServlet implements HttpRequestHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException {
        request.getRequestDispatcher("pages/home/loginpage.jsp").forward(request,response);
    }

}
