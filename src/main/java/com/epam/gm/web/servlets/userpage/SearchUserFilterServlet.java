package com.epam.gm.web.servlets.userpage;

import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by OLEG on 21.06.2015.
 */
public class SearchUserFilterServlet implements HttpRequestHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException {
        System.out.println("SearchUserFilterServlet servlet");

        String tagsInString = request.getParameter("tags");
        String userName = request.getParameter("userNameInput");

        System.out.println("tags : " + tagsInString);
        System.out.println("user name : " + userName);
    }
}
