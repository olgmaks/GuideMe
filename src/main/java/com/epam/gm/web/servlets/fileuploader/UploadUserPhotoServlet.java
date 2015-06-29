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
        
	UploadServletHelper uploadServletHelper = UploadServletHelper.getInstance(
		PhotoService.PATH_TO_USER_PHOTOS, PhotoService.SAVE_USER_PHOTO_URL, PhotoService.serve(),
		SessionRepository.getSessionUser(request).getId(), "user");
	
	if("POST".equals(request.getMethod())){
            System.out.println("post request ");
            uploadServletHelper.doPost(request, response);
            return;
        }else {
            System.out.println("not post method");
        }

        if("GET".equals(request.getMethod())){
            System.out.println("get request ");
            uploadServletHelper.doGet(request,response);
            return;
        }else {
            System.out.println("not get method");
        }
    }
    
    

}
