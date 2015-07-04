package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.ServiceInEventDao;
import com.epam.gm.model.ServiceInEvent;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

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
		System.out.println("**********" + request.getParameter("priceval"));
		System.out.println("**********" + request.getParameter("id"));
		System.out.println(request.getParameter("description"));
		System.out.println(request.getParameter("datefromval"));
		System.out.println(request.getParameter("timefromval"));
		System.out.println(request.getParameter("serviceidval"));
		System.out.println(request.getParameter("positionsval"));
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
		/* serviceInEvent.setEventId(eventId); */

		ServiceInEventDao serviceInEventDao = new ServiceInEventDao();

	}
}
