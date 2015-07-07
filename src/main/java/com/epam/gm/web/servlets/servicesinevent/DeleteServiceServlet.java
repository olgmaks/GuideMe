package com.epam.gm.web.servlets.servicesinevent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.daolayer.ServiceDao;
import com.epam.gm.daolayer.ServiceInEventDao;
import com.epam.gm.model.Service;
import com.epam.gm.model.ServiceInEvent;
import com.epam.gm.model.User;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class DeleteServiceServlet extends HttpServlet implements
		HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -710740028556993959L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		HttpSession session = request.getSession();
		User user = SessionRepository.getSessionUser(request);
		int id = (int) session.getAttribute("eventId");
		Integer currentServiceId = Integer.parseInt(request
				.getParameter("idval"));
		List<ServiceInEvent> list = new ServiceInEventDao()
				.getAllServicesByEventId(id);
		new ServiceInEventDao()
				.updateServiceInEventToDeletedById(currentServiceId);
		session.setAttribute("servicesInEvent", list);

	}
}
