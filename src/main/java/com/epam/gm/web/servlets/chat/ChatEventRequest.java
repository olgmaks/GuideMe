package com.epam.gm.web.servlets.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.MessageEventDao;
import com.epam.gm.model.MessageEvent;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class ChatEventRequest implements HttpRequestHandler {

	MessageEventDao dao;

	public ChatEventRequest() {
		dao = new MessageEventDao();
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, SQLException {
		 request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println(action);
		if(action.equals("getByEvent")){
			int eventId = Integer.parseInt(request.getParameter("eventId"));
			try {
				List<MessageEvent> meList =  dao.getByEvent(eventId);
				String json = new Gson().toJson(meList);
//				for(MessageEvent me : meList){
//					sb.append(me.getSender().getLastName()+ " " + me.getSender().getFirstName() + ": " + me.getMessage() + "\n");
//				}
				response.setCharacterEncoding("UTF-8");
		        response.setContentType("application/json");
				System.out.println(json);
				response.getWriter().write(new Gson().toJson(meList));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if (action.equals("deleteMessage")){
			int messageEventId = Integer.parseInt(request.getParameter("id"));
			dao.deleteById(messageEventId);
		}
	}
}