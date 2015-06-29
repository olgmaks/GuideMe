package com.epam.gm.web.servlets.fileuploader;

import com.epam.gm.services.PhotoService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by OLEG on 27.06.2015.
 */
public class UploadPageServlet implements HttpRequestHandler {
    
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException {
	System.out.println("UploadPageServlet");
	String uploadOwner =  request.getParameter("uploadtype");
	String targerUrl = null;
	String centralContent = "fileupload";

	
	if (uploadOwner.equals("user")) {
	    targerUrl = PhotoService.SAVE_USER_PHOTO_URL;
	}else if (uploadOwner.equals("event")){
	    targerUrl = PhotoService.SAVE_EVENT_PHOTO_URL;
	}
	
	 request.setAttribute("url", targerUrl);
	 request.setAttribute("centralContent", centralContent);
	 request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(request, response);

    }
    
}
