package com.epam.gm.web.servlets.fileuploader;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.services.PhotoService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class UploadUserPhotoServlet implements HttpRequestHandler{
    

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException,IllegalAccessException {
        
	String pathToUserPhoto = PhotoService.PATH_TO_USER_PHOTOS;
	String saveUserPhotoUrl = PhotoService.SAVE_USER_PHOTO_URL;
	PhotoService photoService =PhotoService.serve();
	Integer ownerId = SessionRepository.getSessionUser(request).getId();
	String ownerType = "user";
	
	UploadServletHelper uploadServletHelper = UploadServletHelper.getInstance(
		pathToUserPhoto, saveUserPhotoUrl, photoService, ownerId, ownerType);
	
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
