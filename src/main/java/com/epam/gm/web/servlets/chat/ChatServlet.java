package com.epam.gm.web.servlets.chat;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.User;
import com.epam.gm.services.PhotoService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class ChatServlet implements HttpRequestHandler{
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		try{
			User user = new User();
			user = SessionRepository.getSessionUser(request);
		request.setAttribute("userLogined", user);
		if(user.getId() ==17){
			request.setAttribute("friend", new UserDao().getUserByEmail("z@ukr.net"));
		}else{
			request.setAttribute("friend", new UserDao().getUserByEmail("qwert@ukr.net"));
		}
		request.getRequestDispatcher("chat.jsp").forward(request,
				response);
		}catch(NumberFormatException nfe){
			response.sendRedirect("404.do");
		}
	}
}
