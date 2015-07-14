package com.epam.gm.web.servlets.servicesinevent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.daolayer.PaidServiceDao;
import com.epam.gm.model.PaidService;
import com.epam.gm.model.ServiceInEvent;
import com.epam.gm.model.User;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class BuyServicesServlet extends HttpServlet implements
		HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1230829317898312247L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		HttpSession session = request.getSession();
		List<Integer> servicesInBill = (List<Integer>) session
				.getAttribute("servicesinbilllist");
		List<ServiceInEvent> necessaryServices = (List<ServiceInEvent>) session
				.getAttribute("neseccaryServices");
		Integer eventId = (Integer) session.getAttribute("eventId");
		User user = SessionRepository.getSessionUser(request);

		/*
		 * boughtServices = boughtServices.replaceAll("'", "\""); String[] ar =
		 * boughtServices.split("\\[|\\]|\\,|\"");
		 */
		for (int i = 0; i < necessaryServices.size(); i++) {
			try {
				PaidService paidService = new PaidService();
				paidService.setEventId(eventId);
				paidService.setUserId(user.getId());
				paidService.setServiceInEventId(necessaryServices.get(i)
						.getId());
				new PaidServiceDao().savePaidService(paidService);

			} catch (NumberFormatException e) {
			}
		}

		for (int i = 0; i < servicesInBill.size(); i++) {
			try {
				PaidService paidService = new PaidService();
				paidService.setEventId(eventId);
				paidService.setUserId(user.getId());
				paidService.setServiceInEventId(servicesInBill.get(i));
				paidService.setAccepted(false);
				new PaidServiceDao().savePaidService(paidService);

			} catch (NumberFormatException e) {
			}
		}

		response.sendRedirect(response.encodeRedirectURL(request
				.getContextPath() + "/eventDetail.do?id=" + eventId));
	}
}
