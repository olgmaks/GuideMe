package com.epam.gm.web.servlets.photomod;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.Photo;
import com.epam.gm.services.EventService;
import com.epam.gm.services.PhotoService;
import com.epam.gm.services.UserService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class UpdateEventAvatarServlet implements HttpRequestHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException {
	// TODO Auto-generated method stub
        String path = request.getParameter("location");
        Photo photo = PhotoService.serve().getPhotoByPath(path);
        	
        

        Integer eventId = (Integer)request.getSession(true).getAttribute("eventId");
        Integer photoId = photo.getId();

        EventService.serve().updateEventAvatar(eventId,photoId);

        String requestGet ="eventDetail.do?id="+eventId;

        request.getRequestDispatcher(requestGet).forward(request,response);
    }

}
