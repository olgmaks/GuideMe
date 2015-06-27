package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.services.CommentEventService;
import com.epam.gm.util.ValidateHelper;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class DeleteEventCommentServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		
		System.out.println("DeleteEventCommentServlet");
		
		String id = request.getParameter("id");
		String eventId = request.getParameter("eventId");
		
		System.out.println("id=" + id);
		System.out.println("eventId=" + eventId);
		
		if(id != null && ValidateHelper.isNumber(id) && eventId != null && ValidateHelper.isNumber(eventId)) {
			
			System.out.println("id="+id);
			
			new CommentEventService().deleteById(Integer.parseInt(id.trim()));
			
			System.out.println("redirect to: " + "eventDetail.do?id=" + eventId);
			
			response.sendRedirect("eventDetail.do?id=" + eventId);
			
			return;
		}
		
		System.out.println("Delete message from event not fount page");
		response.sendRedirect("404.do");
		
	}

}
