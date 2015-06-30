package com.epam.gm.web.servlets.photomod;

import com.epam.gm.model.Photo;
import com.epam.gm.services.PhotoService;
import com.epam.gm.services.UserService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateUserAvatarServlet implements HttpRequestHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException {
        // TODO Auto-generated method stub
        String path = request.getParameter("location");
        Photo photo = PhotoService.serve().getPhotoByPath(path);
        UserService userService = UserService.serve();

        Integer sessionUserId = SessionRepository.getSessionUser(request).getId();

        userService.updateUserAvatar(sessionUserId, photo.getId());

        request.getRequestDispatcher("usergallery.do").forward(request,response);
    }

}
