package com.epam.gm.web.servlets.servicesinevent;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.model.Event;
import com.epam.gm.model.Service;
import com.epam.gm.services.EventService;
import com.epam.gm.services.GuideServicesService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class InfoServlet extends HttpServlet implements HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -793861941254725043L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		HttpSession session = request.getSession();
		int eventId = (int) session.getAttribute("eventId");
		System.out.println("****** " + eventId);
		Map<String, Object> map = new HashMap<>();
		Event currentEvent = new EventService().getById(eventId);
		String newString = new SimpleDateFormat("yyyy-MM-dd")
				.format(currentEvent.getDateTo());
		response.setContentType("application/json");
		map.put("eventId", newString);
		response.getWriter().write(new Gson().toJson(map));

	}
}
