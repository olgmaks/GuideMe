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
import com.epam.gm.services.EventService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.util.ValidateHelper;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class ChangeEventStatusServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {

		System.out.println("ChangeEventStatusServlet");

		String id = request.getParameter("id");
		String status = request.getParameter("status");
		if (id != null
				&& ("active".equals(status) || "filled".equals(status)
						|| "cancelled".equals(status) || "done".equals(status))
				&& ValidateHelper.isNumber(id)) {

			EventService eventService = new EventService();
			Event event = eventService.getById(Integer.parseInt(id.trim()));

			if (event == null) {
				response.sendRedirect("404.do");
				return;
			}

			User user = new User();
			user = SessionRepository.getSessionUser(request);

			if (user == null) {
				response.sendRedirect("401.do");
				return;
			}
			
			eventService.changeEventStatus(Integer.parseInt(id.trim()), status);

			//response.sendRedirect("eventDetail.do?id="
			//		+ event.getId().toString());
			
			Map<String, Object> responseMap = new HashMap<>();

	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("application/json");			
			responseMap.put("isValid", true);
			response.getWriter().write(new Gson().toJson(responseMap));			
			
			return;

		}

		// return;
		response.sendRedirect("404.do");
	}

}
