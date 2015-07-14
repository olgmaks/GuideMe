package com.epam.gm.web.servlets.userpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.daolayer.EventDao;
import com.epam.gm.daolayer.UserInEventDao;
import com.epam.gm.model.User;
import com.epam.gm.model.UserInEvent;
import com.epam.gm.services.PhotoService;
import com.epam.gm.services.UserInEventService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

/**
 * Created by OLEG on 14.06.2015.
 */
public class UserCabinetServlet implements HttpRequestHandler {

	private PhotoService photoService;
	// private EventService eventService;
	private UserInEventService userInEventService;

	public UserCabinetServlet() {
		photoService = new PhotoService();
		// eventService = new EventService();
		userInEventService = new UserInEventService();
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		User sessionUser = SessionRepository.getSessionUser(request);
		HttpSession session = request.getSession();

		if (sessionUser == null) {
			response.sendRedirect("401.do");
			return;
		}

		User user = (User) session.getAttribute("sessionUser");
		EventDao eventDao = new EventDao();
		UserInEventDao userInEventDao = new UserInEventDao();
		request.setAttribute(
				"listOfUserInEvent",
				userInEventDao
						.getAllActualUserInEventWhereUserIsMemberAndNotModeratorByUserId(user
								.getId()));
		request.setAttribute("listOfOldUserInEvent",
				userInEventDao
						.getAllOldUserInEventWhereUserNotModeratorByUserId(user
								.getId()));
		request.setAttribute("userRequestsToEvents",
				userInEventDao.getAllWhereUserSentRequestByUserId(user.getId()));

		// gryn
		request.setAttribute("listOfOldModeratorEvents",
				eventDao.getOldAndNotDeletedEventsByModeratorId(user.getId()));

		request.setAttribute("listOfModeratorEvents", eventDao
				.getActiveAndNotDeletedEventsByModeratorId(user.getId()));

		// Photo userPhoto = photoService.getUserPhoto(sessionUser.getId());
		List<UserInEvent> userInEvents = userInEventService
				.getEventsByUserId(sessionUser.getId());

		// Creating map with paths for events photo <eventId, pathToEventPhoto>
		Map<Integer, String> eventPhotosPathMap = new HashMap<>();

		for (UserInEvent iter : userInEvents) {
			System.out.println(iter);
			int eventId = iter.getEventId();
			String pathToEventPhoto = "";
			try {
				pathToEventPhoto = photoService.getEventPhoto(eventId)
						.getPath();
			} catch (Exception e) {
			}
			eventPhotosPathMap.put(eventId, pathToEventPhoto);
		}
		request.setAttribute("centralContent", "eventsincabinet");
		request.setAttribute("userInEvents", userInEvents);
		request.setAttribute("eventPhotosPathMap", eventPhotosPathMap);

		request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(
				request, response);
	}
}
