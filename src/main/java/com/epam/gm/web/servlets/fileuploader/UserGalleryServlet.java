package com.epam.gm.web.servlets.fileuploader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.Photo;
import com.epam.gm.model.User;
import com.epam.gm.services.PhotoService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class UserGalleryServlet implements HttpRequestHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException,IllegalAccessException {
	User sessionUser = SessionRepository.getSessionUser(request);
	
	if (sessionUser==null) {
	    response.sendRedirect("401.do");
	    return;
	}
	
	List<Photo> photos = PhotoService.serve().getUserPhotos(sessionUser.getId());
	request.setAttribute("photos", photos);
	request.setAttribute("centralContent","usergallery");
	request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(request,response);
	
    }

}
