package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.services.MessageUserService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class MessageToAdminRequest implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String action = request.getParameter("action");

		if (action != null) {
			try {
				if (action.equals("sendMessage")) {
					String message = request.getParameter("message");
					new MessageUserService().sendMessageToAdmin(
							SessionRepository.getSessionUser(request).getId(),
							message);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
