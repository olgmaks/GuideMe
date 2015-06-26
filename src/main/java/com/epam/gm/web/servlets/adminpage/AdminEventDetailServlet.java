package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.RatingEventDao;
import com.epam.gm.model.Event;
import com.epam.gm.model.User;
import com.epam.gm.model.UserInEvent;
import com.epam.gm.services.CommentEventService;
import com.epam.gm.services.EventService;
import com.epam.gm.services.PhotoService;
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

			if (event.getModeratorId().equals(user.getId())) {
				// moderator
				

			} else {
				UserInEventService userInEventService = new UserInEventService();
				List<UserInEvent> userInEvent = userInEventService
						.getByEventAndUser(event.getId(), user.getId());

				UserInEvent details = null;
				boolean forbidden = false;
				if (userInEvent == null || userInEvent.isEmpty()) {
					forbidden = true;

				} else {
					details = userInEvent.get(0);
					if (!details.getIsMember())
						forbidden = true;
				}

				if (forbidden) {
					response.sendRedirect("403.do");
					return;
				}
				
				//member
				
				request.setAttribute("mark", mark);
				request.setAttribute("userLogined", user);
				request.setAttribute("commentEvent",
						new CommentEventService().getByEventId(id));
				request.setAttribute("photos",
						new PhotoService().getEventPhotos(id));
				System.out.println(new PhotoService().getEventPhotos(id));
				request.getRequestDispatcher("pages/admin/adminEventDetail.jsp")
						.forward(request, response);

			}

		} catch (NumberFormatException nfe) {
			response.sendRedirect("404.do");
		}
	}
}
