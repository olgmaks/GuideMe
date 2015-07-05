package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.Event;
import com.epam.gm.model.UserInEvent;
import com.epam.gm.services.EventService;
import com.epam.gm.services.UserInEventService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AcceptMemberServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {


		System.out.println("RemoveMemberServlet");
		
		try {

			Integer userId = Integer.valueOf(request
					.getParameter("userFriendId"));
			System.out.println("userId :  " + userId);

			Integer eventId = Integer.valueOf(request
					.getParameter("memberEventId"));

			System.out.println("eventId :" + eventId);
			
			EventService eventService = new EventService();
			Event event = eventService.getById(eventId);
			
			UserInEventService userInEventService = new UserInEventService();
			List<UserInEvent> members = userInEventService
					.getByEventOnlyMembers(event.getId());
			
			int capacityInt = Integer.MAX_VALUE;
			if (event.getParticipants_limit() != null
					&& event.getParticipants_limit() > 0) {
				capacityInt = event.getParticipants_limit();
			}

			userInEventService.acceptToEvent(eventId, userId);
			
			if (members.size() + 1 >= capacityInt) {
				//filled
				eventService.changeEventStatus(event.getId(), "filled");
			}	
			eventService.fixEventLimit(event.getId());

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("404.do");
		}
		
	}

	public static void main(String[] args) {
	}

}
