package com.epam.gm.web.servlets.userpage;

import com.epam.gm.model.Photo;
import com.epam.gm.model.User;
import com.epam.gm.services.PhotoService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by OLEG on 14.06.2015.
 */
public class UserCabinetServlet implements HttpRequestHandler {

    private PhotoService photoService;

    public UserCabinetServlet() {
        photoService = new PhotoService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        User user = (User) request.getSession(true).getAttribute(SessionRepository.SESSION_USER);
        Photo userPhoto = photoService.getUserPhoto(user.getId());
        if (userPhoto != null) {
            request.setAttribute("userPhoto", userPhoto);
        }
        request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(request, response);
    }
}
