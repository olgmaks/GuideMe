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
 * Created by OLEG on 25.06.2015.
 */
public class SendFriendRequest implements HttpRequestHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException, SQLException, IllegalAccessException {

        Integer sessionUserId = SessionRepository.getSessionUser(request).getId();
        Integer friendId = Integer.valueOf(request.getParameter("friendId"));



        FriendUserService.serve().sendFriendRequest(sessionUserId,friendId);
        System.out.println(String.format("friend request has been sent user id = %s, friend id = %s",sessionUserId,friendId));
    }

}
