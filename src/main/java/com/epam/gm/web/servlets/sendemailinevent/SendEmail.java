package com.epam.gm.web.servlets.sendemailinevent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.EventDao;
import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.Event;
import com.epam.gm.model.User;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.util.SendMailTLS;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class SendEmail extends HttpServlet implements HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -29219979193464763L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {

		String allCheckedUsers = request.getParameter("usercheckedtosendval");
		String message = request.getParameter("textmessagetosendval");
		allCheckedUsers = allCheckedUsers.replaceAll("'", "\"");
		System.out.println("************" + message);
		String[] ar = allCheckedUsers.split("\\[|\\]|\\,|\"");

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < ar.length; i++) {
			/* System.out.println(ar[i]); */
			try {
				int boughtId = Integer.parseInt(ar[i]);
				list.add(boughtId);
			} catch (NumberFormatException e) {
			}
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		int eventId = (int) request.getSession(true).getAttribute("eventId");

		Event event = new EventDao().getEventById(eventId);

		for (int i = 0; i < list.size(); i++) {
			User user = new UserDao().getUserById(list.get(i));
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					SendMailTLS.sendMessage(user.getEmail(), "Guide Me\n"
							+ event.getName(), message);
				}
			});

		}

	}
}
