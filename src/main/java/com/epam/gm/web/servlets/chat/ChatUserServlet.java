package com.epam.gm.web.servlets.chat;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.services.FriendUserService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class ChatUserServlet implements HttpRequestHandler{
	private FriendUserService friendUserService;

    public ChatUserServlet() {
        friendUserService = new FriendUserService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        request.setAttribute("userFriends", friendUserService.getUserFriends(
                SessionRepository.getSessionUser(request).getId()));


        request.setAttribute("centralContent", "usermessages");
        request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(request, response);
    }
}
