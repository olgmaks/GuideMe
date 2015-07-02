package com.epam.gm.web.servlets.photomod;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.services.PhotoService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class DeleteUserPhotoServlet implements HttpRequestHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException {

        String photoLocation = request.getParameter("location");
        System.out.println("photolocation : " + photoLocation);

        PhotoService.serve().deletePhotoByPath(photoLocation);
        
        File file = new File(photoLocation);

        System.out.println("delete user success photoPass = '"+photoLocation+"'");

    }

}
