package com.epam.gm.web.servlets.fileuploader;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.services.PhotoService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class UploadEventPhotoServlet implements HttpRequestHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException,IllegalAccessException {
	
	
	String pathToEventPhoto = PhotoService.PATH_TO_EVENT_PHOTOS;
	String saveEventPhotoUrl = PhotoService.SAVE_EVENT_PHOTO_URL;
	PhotoService photoService =PhotoService.serve();
	Integer ownerId = (Integer) request.getSession(true).getAttribute("eventId");
	String ownerType = "event";
	
	System.out.println(String.format("******upload event photo servlet %s %s %s %s",pathToEventPhoto,saveEventPhotoUrl,"ownerId",ownerId));
	
	UploadServletHelper uploadServletHelper = UploadServletHelper.getInstance(
		pathToEventPhoto, saveEventPhotoUrl, photoService, ownerId, ownerType);
	
	if("POST".equals(request.getMethod())){
            System.out.println("post request ");
            uploadServletHelper.doPost(request, response);
            return;
        }

        if("GET".equals(request.getMethod())){
            System.out.println("get request ");
            uploadServletHelper.doGet(request,response);
            return;
        }
	
	
    }

   

}
