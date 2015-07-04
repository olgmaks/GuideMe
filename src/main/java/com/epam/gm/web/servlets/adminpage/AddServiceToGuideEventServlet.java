package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.daolayer.EventDao;
import com.epam.gm.daolayer.ServiceDao;
import com.epam.gm.daolayer.ServiceInEventDao;
import com.epam.gm.model.Event;
import com.epam.gm.model.Service;
import com.epam.gm.model.ServiceInEvent;
import com.epam.gm.services.EventService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class AddServiceToGuideEventServlet extends HttpServlet implements
		HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 551117376281492482L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		HttpSession session = request.getSession();
		System.out.println("**********" + request.getParameter("priceval"));
		System.out.println("**********" + request.getParameter("id"));
		System.out.println(request.getParameter("description"));
		System.out.println(request.getParameter("datefromval"));
		System.out.println(request.getParameter("timefromval"));
		System.out.println(request.getParameter("serviceidval"));
		System.out.println(request.getParameter("positionsval"));
		String description = request.getParameter("description");
		Integer price = null;
		try {
			price = Integer.parseInt(request.getParameter("priceval"));
		} catch (NumberFormatException e) {
			price = 0;
		}
		ServiceInEvent serviceInEvent = new ServiceInEvent();
		if (request.getParameter("datefromval") != null
				|| request.getParameter("datefromval") != ""
				|| !request.getParameter("datefromval").equals("")) {
			String fullDateFrom = request.getParameter("datefromval") + "-"
					+ request.getParameter("timefromval");
			DateFormat formatFrom = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
			Date dateFrom = null;
			try {
				dateFrom = formatFrom.parse(fullDateFrom);
			} catch (ParseException e) {
				dateFrom = null;
			}
			serviceInEvent.setDateFrom(dateFrom);
		}
		if (request.getParameter("datetoval") != null
				|| request.getParameter("datetoval") != ""
				|| !request.getParameter("datefromval").equals("")) {
			if (serviceInEvent != null) {
				String fullDateTo = request.getParameter("datetoval") + "-"
						+ request.getParameter("timetoval");
				DateFormat formatTo = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
				Date dateTo = null;
				try {
					dateTo = formatTo.parse(fullDateTo);
				} catch (ParseException e) {
					dateTo = null;

				}
				serviceInEvent.setDateTo(dateTo);
			}
		}
		int idEvent = (int) session.getAttribute("eventId");
		int idService = Integer.parseInt(request.getParameter("serviceidval"));
		int amountOfPosition = 0;
		try {
			amountOfPosition = Integer.parseInt(request
					.getParameter("positionsval"));
		} catch (NumberFormatException e) {
			amountOfPosition = -1;
		}
		Event event = new EventService().getById(idEvent);
		ServiceDao serviceDao = new ServiceDao();
		Service service = serviceDao.getServiceById(idService);
		boolean isChanged = false;
		if (!service.getDescription().equals(description)) {
			isChanged = true;
			serviceDao.updateServiceDescriptionById(idService, description);
		}
		if (!service.getPrice().equals(price)) {
			isChanged = true;
			serviceDao.updateServicePriceById(idService, price);
		}
		serviceInEvent.setServiceId(idService);
		serviceInEvent.setEventId(event.getId());
		serviceInEvent.setAvailableAmountOfPositions(amountOfPosition);
		/* serviceInEvent.setEventId(eventId); */
		ServiceInEventDao serviceInEventDao = new ServiceInEventDao();
		serviceInEventDao.saveServiceInEvent(serviceInEvent);

		Map<String, Object> map = new HashMap<>();

		response.setContentType("application/json");
		map.put("isChanged", isChanged);
		// map.put("name", name);
		response.getWriter().write(new Gson().toJson(map));
	}
}
