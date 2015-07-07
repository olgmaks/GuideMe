package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.StringJoiner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.calculators.EventCalculator;
import com.epam.gm.calculators.UserCalculator;
import com.epam.gm.daolayer.RatingEventDao;
import com.epam.gm.daolayer.ServiceDao;
import com.epam.gm.daolayer.ServiceInEventDao;
import com.epam.gm.dateparser.DateParser;
import com.epam.gm.model.Country;
import com.epam.gm.model.Event;
import com.epam.gm.model.Language;
import com.epam.gm.model.Service;
import com.epam.gm.model.Tag;
import com.epam.gm.model.User;
import com.epam.gm.model.UserInEvent;
import com.epam.gm.services.CommentEventService;
import com.epam.gm.services.CountryService;
import com.epam.gm.services.EventService;
import com.epam.gm.services.LanguageService;
import com.epam.gm.services.PhotoService;
import com.epam.gm.services.TagService;
import com.epam.gm.services.UserInEventService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AdminEventDetailServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		request.setCharacterEncoding("UTF-8");
		RatingEventDao reDao = new RatingEventDao();
		System.out.println("eventDetailservlet");
		try {
			HttpSession session = request.getSession();
			User user = new User();
			user = SessionRepository.getSessionUser(request);

			if (user == null) {
				response.sendRedirect("401.do");
				return;
			}

			if (user.isGuide()) {
				List<Service> list = new ServiceDao().getServicesByGuideId(user
						.getId());
				request.setAttribute("listOfServices", list);
			}
			EventService eventService = new EventService();
			int id = Integer.parseInt(request.getParameter("id"));
			Event event = new Event();
			event = eventService.getById(id);

			eventService.buildTagString(event);

			LanguageService languageService = new LanguageService();
			List<Language> languageList = languageService.getLocalizedLangs();
			request.setAttribute("languageList", languageList);

			request.setAttribute("servicesInEvent",
					new ServiceInEventDao().getAllServicesByEventId(id));

			// gryn - adding try
			Integer sessionUserId = null;
			try {
				request.getSession(true).setAttribute("eventId", id);
				sessionUserId = SessionRepository.getSessionUser(request)
						.getId();
			} catch (Exception e) {
				response.sendRedirect("401.do");
				return;
			}

			Boolean showUploadAnchor = UserInEventService.serve()
					.isMemberOfEvent(sessionUserId, id);
			request.setAttribute("showUploadAnchor", showUploadAnchor);

			CountryService countryService = new CountryService();
			List<Country> countryList = countryService.getAll();
			request.setAttribute("countryList", countryList);

			request.setAttribute("dateFrom",
					DateParser.SqlDateToString(event.getDateFrom()));
			request.setAttribute("dateTo",
					DateParser.SqlDateToString(event.getDateTo()));
			request.setAttribute("hourFrom",
					DateParser.SqlHourToString(event.getDateFrom()));
			request.setAttribute("hourTo",
					DateParser.SqlHourToString(event.getDateTo()));
			request.setAttribute("minuteFrom",
					DateParser.SqlMinuteToString(event.getDateFrom()));
			request.setAttribute("minuteTo",
					DateParser.SqlMinuteToString(event.getDateTo()));

			request.setAttribute("event", event);

			UserInEventService userInEventService = new UserInEventService();

			List<UserInEvent> userInEvent = userInEventService
					.getByEventAndUser(event.getId(), user.getId());
			if (!userInEvent.isEmpty()) {
				if (userInEvent.get(0).getStatus().equalsIgnoreCase("resident")) {
					request.setAttribute("getstatus", "resident");
				} else if (userInEvent.get(0).getStatus()
						.equalsIgnoreCase("guest")) {
					request.setAttribute("getstatus", "guest");
				}
				if (userInEvent.get(0).getBedCount() >= 0) {
					request.setAttribute("apartments", "accept");
					request.setAttribute("getbet_count", userInEvent.get(0)
							.getBedCount());
				} else if (userInEvent.get(0).getBedCount() < 0) {
					request.setAttribute("apartments", "need");
					request.setAttribute("getbet_count", 0 - userInEvent.get(0)
							.getBedCount());
				}
			}

			Integer mark = null;
			if (user != null) {
				if (reDao.getMarkByEvent(id, user.getId()) != null) {
					mark = reDao.getMarkByEvent(id, user.getId()).getMark();
				}

			}

			// // gryn
			// else {
			// response.sendRedirect("401.do");
			// return;
			// }
			//
			// // grn
			// if (event == null) {
			// response.sendRedirect("404.do");
			// return;
			// }

			boolean isModerator = event.getModeratorId().equals(user.getId());
			request.setAttribute("isModerator", isModerator);

			// if (isModerator) {
			// moderator

			// } else {

			boolean showJoin = false;
			boolean showQuit = false;
			boolean showCancel = false;

			UserInEvent details = null;
			boolean isMember = true;
			if (userInEvent == null || userInEvent.isEmpty()) {
				isMember = false;

				showJoin = true;

				if (!"active".equals(event.getStatus())) {
					showJoin = false;
				}

			} else {
				details = userInEvent.get(0);
				if (!details.getIsMember())
					isMember = false;

				showQuit = isMember;
				showCancel = !isMember;
			}

			// member
			request.setAttribute("isMember", isMember);

			System.out.println("showQuit = " + showQuit);
			System.out.println("showJoin = " + showJoin);
			System.out.println("showCancel = " + showCancel);

			List<UserInEvent> members = userInEventService
					.getByEventOnlyMembers(event.getId());
			request.setAttribute("members", members);

			Integer totalBed = 0;
			for (UserInEvent m : members) {
				totalBed += m.getBedCount();

				// System.out.println("%%%%%%%%%%%%%%%%% " + m.getBedCount());

			}

			String totalBedStr = "";
			if (totalBed > 0) {
				totalBedStr = "Accepting guests: " + totalBed;
			} else if (totalBed < 0) {
				totalBedStr = "Need lodjing: " + (-totalBed);
			}

			// System.out.println("#########totalBed = " + totalBed);

			request.setAttribute("totalBed", totalBed);
			request.setAttribute("totalBedStr", totalBedStr);

			StringJoiner capacity = new StringJoiner(" / ", "", "");
			capacity.setEmptyValue("");

			capacity.add(((Integer) members.size()).toString());

			int capacityInt = Integer.MAX_VALUE;
			if (event.getParticipants_limit() != null
					&& event.getParticipants_limit() > 0) {
				capacity.add(event.getParticipants_limit().toString());

				capacityInt = event.getParticipants_limit();
			}

			request.setAttribute("capacity", capacity.toString());

			if (members.size() + 1 > capacityInt)
				showJoin = false;

			System.out.println("showQuit = " + showQuit);
			System.out.println("showJoin = " + showJoin);
			System.out.println("showCancel = " + showCancel);

			List<UserInEvent> requests = userInEventService
					.getByEventOnlyRequesters(event.getId());
			request.setAttribute("requests", requests);

			// System.out.println("++++++members = " + members);

			request.setAttribute("mark", mark);
			request.setAttribute("userLogined", user);
			request.setAttribute("commentEvent",
					new CommentEventService().getByEventId(id));
			request.setAttribute("photos",
					new PhotoService().getEventPhotos(id));

			request.setAttribute("isAdmin", SessionRepository.isAdmin(request));
			
			
			UserCalculator userCalc = new UserCalculator(
					event.getModeratorId(), user.getId());
			Integer moderatorMark = 0;

			request.setAttribute(
					"moderatorMark",
					"Average user mark: "
							+ Math.round(userCalc.getAverageRate())
							+ "  Total points: "
							+ Math.round(userCalc.calculate()));

			EventCalculator eventCalc = new EventCalculator(event.getId(),
					user.getId());

			request.setAttribute("eventMark",
					Math.round(eventCalc.getAverageRate()));

			request.setAttribute("eventMark",
					Math.round(eventCalc.getAverageRate()));
			request.setAttribute("eventPoints",
					Math.round(eventCalc.calculate()));

			request.setAttribute("showQuit", showQuit);
			request.setAttribute("showJoin", showJoin);
			request.setAttribute("showCancel", showCancel);
			request.setAttribute("type",
					eventCalc.isModeratorGuide() ? "excursion" : "event");

			request.setAttribute("selActive",
					"active".equals(event.getStatus()) ? "selected" : "");
			request.setAttribute("selFilled",
					"filled".equals(event.getStatus()) ? "selected" : "");
			request.setAttribute("selCancelled",
					"cancelled".equals(event.getStatus()) ? "selected" : "");
			request.setAttribute("selDone",
					"done".equals(event.getStatus()) ? "selected" : "");

			List<Tag> tags = new TagService().getAllEventTags(event.getId());

			request.setAttribute("tags", tags);

			System.out.println(new PhotoService().getEventPhotos(id));
			request.getRequestDispatcher("pages/admin/adminEventDetail.jsp")
					.forward(request, response);

			// }

		} catch (NumberFormatException nfe) {

			response.sendRedirect("404.do");
		}
	}
}