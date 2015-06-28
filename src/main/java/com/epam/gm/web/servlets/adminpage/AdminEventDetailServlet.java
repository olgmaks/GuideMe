package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.calculators.EventCalculator;
import com.epam.gm.calculators.UserCalculator;
import com.epam.gm.daolayer.RatingEventDao;
import com.epam.gm.model.Event;
import com.epam.gm.model.RatingUser;
import com.epam.gm.model.User;
import com.epam.gm.model.UserInEvent;
import com.epam.gm.services.CommentEventService;
import com.epam.gm.services.EventService;
import com.epam.gm.services.PhotoService;
import com.epam.gm.services.RatingUserService;
import com.epam.gm.services.UserInEventService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.eventsincabitnet.EventsInCabinetServlet;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AdminEventDetailServlet implements HttpRequestHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		RatingEventDao reDao = new RatingEventDao();
		System.out.println("eventDetailservlet");
		try {
			User user = new User();
			user = SessionRepository.getSessionUser(request);
			EventService eventService = new EventService();
			int id = Integer.parseInt(request.getParameter("id"));

			// gryn
			Event event = eventService.getById(id);

			request.setAttribute("event", event);
			Integer mark = null;
			if (user != null) {
				if (reDao.getMarkByEvent(id, user.getId()) != null) {
					mark = reDao.getMarkByEvent(id, user.getId()).getMark();
				}
			}
			// gryn
			else {
				response.sendRedirect("401.do");
				return;
			}

			// grn
			if (event == null) {
				response.sendRedirect("404.do");
				return;
			}
			
			boolean isModerator = event.getModeratorId().equals(user.getId());
			request.setAttribute("isModerator", isModerator);

			//if (isModerator) {
				// moderator
				

			//} else {
				UserInEventService userInEventService = new UserInEventService();
				List<UserInEvent> userInEvent = userInEventService
						.getByEventAndUser(event.getId(), user.getId());

				boolean showJoin = false;
				boolean showQuit = false;
				boolean showCancel = false;
				
				UserInEvent details = null;
				boolean isMember = true;
				if (userInEvent == null || userInEvent.isEmpty()) {
					isMember = false;
					
					showJoin = true;

				} else {
					details = userInEvent.get(0);
					if (!details.getIsMember())
						isMember = false;
					
					showQuit = isMember;
					showCancel = !isMember;
				}

				//member
				request.setAttribute("isMember", isMember);

				
				System.out.println("showQuit = " + showQuit);
				System.out.println("showJoin = " + showJoin);
				System.out.println("showCancel = " + showCancel);
				
				
				List<UserInEvent> members = userInEventService.getByEventOnlyMembers(event.getId());
				request.setAttribute("members", members);
				
				List<UserInEvent> requests = userInEventService.getByEventOnlyRequesters(event.getId());
				request.setAttribute("requests", requests);				
				
				//System.out.println("++++++members = " + members);
				
				request.setAttribute("mark", mark);
				request.setAttribute("userLogined", user);
				request.setAttribute("commentEvent",
						new CommentEventService().getByEventId(id));
				request.setAttribute("photos",
						new PhotoService().getEventPhotos(id));
				
				request.setAttribute("isAdmin", SessionRepository.isAdmin(request));
				
				
				UserCalculator userCalc = new UserCalculator(event.getModeratorId(), user.getId());
				Integer moderatorMark = 0;

				
				request.setAttribute("moderatorMark", "Average user mark: " + Math.round(userCalc.getAverageRate()) + 
						"  Total points: " + Math.round(userCalc.calculate()));
				
				EventCalculator eventCalc = new EventCalculator(event.getId(), user.getId());
				
				request.setAttribute("eventMark", Math.round(eventCalc.getAverageRate()));
				
				
				request.setAttribute("eventMark", Math.round(eventCalc.getAverageRate()));
				request.setAttribute("eventPoints", Math.round(eventCalc.calculate()));
				
				request.setAttribute("showQuit", showQuit);
				request.setAttribute("showJoin", showJoin);
				request.setAttribute("showCancel", showCancel);	
				request.setAttribute("type", eventCalc.isModeratorGuide() ? "excursion" : "event");
				
				request.setAttribute("selActive", "active".equals(event.getStatus()) ? "selected" : "");
				request.setAttribute("selFilled", "filled".equals(event.getStatus()) ? "selected" : "");
				request.setAttribute("selCancelled", "cancelled".equals(event.getStatus()) ? "selected" : "");
				request.setAttribute("selDone", "done".equals(event.getStatus()) ? "selected" : "");
				
				System.out.println(new PhotoService().getEventPhotos(id));
				request.getRequestDispatcher("pages/admin/adminEventDetail.jsp")
						.forward(request, response);
				
				

			//}

		} catch (NumberFormatException nfe) {
			response.sendRedirect("404.do");
		}
	}
}
