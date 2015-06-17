package com.epam.gm.web.servlets.userpage;

import com.epam.gm.services.FriendUserService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by OLEG on 16.06.2015.
 */
public class UserFriendsServlet implements HttpRequestHandler {

    private FriendUserService friendUserService;

    public UserFriendsServlet() {
        friendUserService = new FriendUserService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        request.setAttribute("userFriends", friendUserService.getUserFriends(
                SessionRepository.getSessionUser(request).getId()));
        request.setAttribute("centralContent", "friends");
        request.getRequestDispatcher("pages/user/userfriends.jsp").forward(request, response);
    }


}
