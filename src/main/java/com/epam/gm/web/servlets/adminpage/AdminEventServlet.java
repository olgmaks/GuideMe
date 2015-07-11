package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.User;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AdminEventServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		User user = new User();
		user = SessionRepository.getSessionUser(request);

		if (user == null) {
			response.sendRedirect("401.do");
			return;
		}
		request.getRequestDispatcher("pages/adminEvent.jsp").forward(request,
				response);
	}
	
//	DECLARE @userID INT ;
//	SET @userId = 1;
//	SELECT 'create event ', e.name AS name
//	  FROM event e
//	  WHERE e.moderator_id = @userID
//	UNION
//	SELECT 'comment user', ce.comment
//	  FROM comment_event ce
//	WHERE ce.commentator_id = @userID
//	UNION 
//	  SELECT 'comment user', cu.comment
//	    FROM comment_user cu
//	    WHERE cu.commentator_id = cu.user_id;


}