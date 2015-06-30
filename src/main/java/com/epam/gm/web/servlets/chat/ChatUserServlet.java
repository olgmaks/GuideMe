package com.epam.gm.web.servlets.chat;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.MessageUserDao;
import com.epam.gm.model.User;
import com.epam.gm.services.FriendUserService;
import com.epam.gm.services.MessageUserService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class ChatUserServlet implements HttpRequestHandler{
	private FriendUserService friendUserService;
	private MessageUserService mus;
    public ChatUserServlet() {
    	mus = new MessageUserService();
        friendUserService = new FriendUserService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	User user = SessionRepository.getSessionUser(request);
        request.setAttribute("userFriends", friendUserService.getUserFriends(
                user.getId()));
        request.setAttribute("numberNewMessage", new MessageUserDao().getCountUnreadeMessage(user.getId()));
        request.setAttribute("lastMessanger", mus.getLastMessanegr(user.getId()));
        request.setAttribute("centralContent", "usermessages");
        request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(request, response);
    }
}
