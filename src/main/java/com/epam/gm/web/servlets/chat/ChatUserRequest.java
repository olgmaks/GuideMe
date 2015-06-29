package com.epam.gm.web.servlets.chat;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.MessageEventDao;
import com.epam.gm.daolayer.MessageUserDao;
import com.epam.gm.model.MessageEvent;
import com.epam.gm.model.MessageUser;
import com.epam.gm.model.User;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class ChatUserRequest implements HttpRequestHandler {

	MessageUserDao dao;

	public ChatUserRequest() {
		dao = new MessageUserDao();
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User user = SessionRepository.getSessionUser(request); 
		 request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println(action);
		if(action.equals("getByUser")){
			try {
				List<MessageUser> meList =  dao.getByUser(user.getId(), Integer.parseInt(request.getParameter("userId")));
				String json = new Gson().toJson(meList);
				response.setCharacterEncoding("UTF-8");
		        response.setContentType("application/json");
				//System.out.println(json);
				response.getWriter().write(new Gson().toJson(meList));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}}