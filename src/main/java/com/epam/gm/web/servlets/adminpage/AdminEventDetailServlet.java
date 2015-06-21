package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epam.gm.daolayer.RatingEventDao;
import com.epam.gm.model.User;
import com.epam.gm.services.CommentEventService;
import com.epam.gm.services.EventService;
import com.epam.gm.services.PhotoService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AdminEventDetailServlet implements HttpRequestHandler{
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		RatingEventDao reDao = new RatingEventDao();
		System.out.println("eventDetailservlet");
		try{
			User user = new User();
			user = SessionRepository.getSessionUser(request);
		EventService eventService = new EventService();
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("event", eventService.getById(id));
		Integer mark = null;
		if (user != null){
			if (reDao.getMarkByEvent(id, user.getId()) != null){
				mark =  reDao.getMarkByEvent(id, user.getId()).getMark();
			}
		}
		request.setAttribute("mark", mark);
		request.setAttribute("userLogined", user);
		request.setAttribute("commentEvent", new CommentEventService().getByEventId(id));
		request.setAttribute("photos", new PhotoService().getEventPhotos(id));
		System.out.println(new PhotoService().getEventPhotos(id));
		request.getRequestDispatcher("pages/admin/adminEventDetail.jsp").forward(request,
				response);
		}catch(NumberFormatException nfe){
			response.sendRedirect("404.do");
		}
	}
}
