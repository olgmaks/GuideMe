package com.epam.gm.web.servlets.userpage;

import com.epam.gm.services.FriendUserService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by OLEG on 18.06.2015.
 */
public class AcceptFriendRequestServlet implements HttpRequestHandler {

    private FriendUserService friendUserService;

    public AcceptFriendRequestServlet(){
        friendUserService = new FriendUserService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException {
        System.out.println("accept friend request serlet");
        Integer friendUserRequestId = Integer.valueOf(request.getParameter("userFriendId"));

        friendUserService.acceptFriendRequest(friendUserRequestId);

        request.setAttribute("userFriendRequestType", "incoming");
        request.setAttribute("centralContent", "friends");
        request.getRequestDispatcher("userfriends.do").forward(request, response);
    }
}
