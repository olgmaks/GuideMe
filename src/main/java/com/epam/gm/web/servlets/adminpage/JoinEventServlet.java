package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

public class JoinEventServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		
		System.out.println("JoinEventServlet");
		
		String id = request.getParameter("id");
		String operation = request.getParameter("operation");
		if(id != null && ("join".equals(operation) || "quit".equals(operation)) && ValidateHelper.isNumber(id)) {
			EventService eventService = new EventService();
			Event event = eventService.getById(Integer.parseInt(id.trim()));
			UserInEventService userInEventService = new UserInEventService();
			
			if(event == null) {
				response.sendRedirect("404.do");
				return;
			}
			
			User user = new User();
			user = SessionRepository.getSessionUser(request);			
			
			if(user == null) {
				response.sendRedirect("401.do");
				return;
			}
			
			if("quit".equals(operation)) {
				userInEventService.deleteUserFromEvent(event.getId(), user.getId());
				response.sendRedirect("eventDetail.do?id=" + event.getId().toString());
				return;				
			} else if("join".equals(operation)) {
				String status = request.getParameter("status");
				String bedCount = request.getParameter("bedCount");
				String bedCountSelect = request.getParameter("bedCountSelect");
				
				System.out.println("status="+status);
				System.out.println("bedCount="+bedCount);
				System.out.println("bedCountSelect="+bedCountSelect);
				
				
				if(bedCount != null && ("resident".equals(status) || "guest".equals(status) )
						&& ValidateHelper.isNumber(bedCount) 
						&& ("accept".equals(bedCountSelect) || "need".equals(bedCountSelect))) {
					
					List<UserInEvent> temp = userInEventService.getByEventAndUser(event.getId(), user.getId());
					
					//not exists already
					if(temp == null || temp.isEmpty()) {
						
						System.out.println("userInEventService.joinToEvent");
						
						userInEventService.joinToEvent(event.getId(), user.getId(), 
								Integer.parseInt(bedCount) * ("need".equals(bedCountSelect) ? -1 : 1), status);
					}
					
					response.sendRedirect("eventDetail.do?id=" + event.getId().toString());
					return;
				}
			
			}	
				
				
			//return;
		}
		
		response.sendRedirect("404.do");
		
	}
	
}
