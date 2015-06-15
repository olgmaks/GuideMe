package com.epam.gm.web.servlets.userpage;

import com.epam.gm.model.Photo;
import com.epam.gm.model.User;
import com.epam.gm.model.UserInEvent;
import com.epam.gm.services.EventService;
import com.epam.gm.services.PhotoService;
import com.epam.gm.services.UserInEventService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by OLEG on 14.06.2015.
 */
public class UserCabinetServlet implements HttpRequestHandler {

    private PhotoService photoService;
    private EventService eventService;
    private UserInEventService userInEventService;


    public UserCabinetServlet() {
        photoService = new PhotoService();
        eventService = new EventService();
        userInEventService = new UserInEventService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        User user = SessionRepository.getSessionUser(request);

        if (user != null) {

            Photo userPhoto = photoService.getUserPhoto(user.getId());
            List<UserInEvent> userInEvents = userInEventService.getEventsByUserId(user.getId());

            request.setAttribute("userInEvents", userInEvents);

            if (userPhoto != null) {
                request.setAttribute("userPhoto", userPhoto);
            } else {
                Photo photo = new Photo();
                photo.setPath("img/unknownuserphoto.png");
                request.setAttribute("userPhoto", photo);
            }
        }
        request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(request, response);
    }
}
