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
public class DeclineFriendRequestServlet implements HttpRequestHandler {

    private FriendUserService  friendUserService;

    public DeclineFriendRequestServlet(){
        friendUserService = new FriendUserService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException {
//        System.out.println("decline friend-user-id : " + request.getParameter("userFriendId"));
        Integer friendUserRequestId = Integer.valueOf(request.getParameter("userFriendId"));
//        System.out.println("friendUserRequestId : " +friendUserRequestId);

        friendUserService.declineFriendRequest(friendUserRequestId);

//        request.setAttribute("userFriendRequestType", "incoming");
//        request.setAttribute("centralContent", "friends");
//        request.getRequestDispatcher("userfriends.do").forward(request, response);
    }
}
