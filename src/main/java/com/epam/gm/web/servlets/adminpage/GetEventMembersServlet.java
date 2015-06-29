package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.Event;
import com.epam.gm.model.User;
import com.epam.gm.model.UserInEvent;
import com.epam.gm.services.EventService;
import com.epam.gm.services.UserInEventService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.util.ValidateHelper;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class GetEventMembersServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {

		System.out.println("GetEventMembersServlet");
		
		String id = request.getParameter("id");
		System.out.println(id);
		
		if(id != null && ValidateHelper.isNumber(id)) {
			UserInEventService userInEventService = new UserInEventService();
			List<UserInEvent> members = userInEventService.getByEventOnlyMembers(Integer.parseInt(id.trim()));
			
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("application/json");
	        
	        Map<String, Object> responseMap = new HashMap<>();

	        responseMap.put("isEmpty", members.isEmpty());
	        responseMap.put("results", members);
	        response.getWriter().write(new Gson().toJson(responseMap));			
			
	        
	        
	        System.out.println("============================");
	        System.out.println(new Gson().toJson(responseMap));
	        System.out.println("============================");
	        
			return;
		}
		
		response.sendRedirect("404.do");
		
	}

}
