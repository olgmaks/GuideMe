package com.epam.gm.web.servlets.userpage;

import com.epam.gm.services.UserService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by OLEG on 20.06.2015.
 */
public class SearchUserServlet implements HttpRequestHandler {

    private UserService userService;

    public SearchUserServlet(){
        userService = new UserService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, IllegalAccessException {
        System.out.println("search user servlet ...");


        request.setAttribute("users", userService.searchNonFriendsUsers(SessionRepository.
                getSessionUser(request).getId()));
        request.setAttribute("centralContent", "searchuser");
        request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(request, response);
    }
}
