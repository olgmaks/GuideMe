package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.services.EventService;
import com.epam.gm.services.FriendUserService;
import com.epam.gm.services.PhotoService;
import com.epam.gm.services.UserTypeService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AdminEventDetailServlet implements HttpRequestHandler{
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		System.out.println("eventDetailservlet");
		try{
		EventService eventService = new EventService();
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("event", eventService.getById(id));
		request.setAttribute("photos", new PhotoService().getEventPhotos(id));
		System.out.println(new PhotoService().getEventPhotos(id));
		request.getRequestDispatcher("pages/admin/adminEventDetail.jsp").forward(request,
				response);
		}catch(NumberFormatException nfe){
			response.sendRedirect("404.do");
		}
	}
}
