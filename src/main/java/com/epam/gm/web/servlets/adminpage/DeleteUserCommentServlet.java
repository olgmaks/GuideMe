package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.services.CommentEventService;
import com.epam.gm.services.CommentUserService;
import com.epam.gm.util.ValidateHelper;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class DeleteUserCommentServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {


		System.out.println("DeleteUserCommentServlet");
		
		String id = request.getParameter("id");
		String userId = request.getParameter("userId");
		
		System.out.println("id=" + id);
		System.out.println("userId=" + userId);
		
		if(id != null && ValidateHelper.isNumber(id) && userId != null && ValidateHelper.isNumber(userId)) {
			
			System.out.println("id="+id);
			
			new CommentUserService().deleteById(Integer.parseInt(id.trim()));
			
			System.out.println("redirect to: " + "eventDetail.do?id=" + userId);
			
			response.sendRedirect("userProfile.do?id=" + userId);
			
			return;
		}
		
		System.out.println("Delete message from user not fountd page");
		response.sendRedirect("404.do");
		
	}
	
}
