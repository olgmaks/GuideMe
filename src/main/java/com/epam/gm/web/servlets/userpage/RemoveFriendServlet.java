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
public class RemoveFriendServlet implements HttpRequestHandler {

    private FriendUserService friendUserService;

    public RemoveFriendServlet(){
        friendUserService = new FriendUserService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

    }
}
