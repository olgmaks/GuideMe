package com.epam.gm.web.servlets.userpage;

import com.epam.gm.model.FriendUser;
import com.epam.gm.services.FriendUserService;
import com.epam.gm.services.UserService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by OLEG on 18.06.2015.
 */
public class UserFriendRequestReceivedServlet implements HttpRequestHandler {

    private FriendUserService friendUserService;
    private UserService userService;

    public UserFriendRequestReceivedServlet() {
        friendUserService = new FriendUserService();
        userService = new UserService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        Integer sessionUserId = SessionRepository.getSessionUser(request).getId();

        List<FriendUser> userRequestsFromFriends = friendUserService.
                getUserRequestsFromFriends(sessionUserId);

        request.setAttribute("userFriends", userRequestsFromFriends);
        request.setAttribute("userFriendRequestType", "incoming");

        request.setAttribute("recommendedFriends", userService.getUserFriendsOfFriends(sessionUserId));

        request.setAttribute("centralContent", "friends");
        request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(request, response);
    }
}
