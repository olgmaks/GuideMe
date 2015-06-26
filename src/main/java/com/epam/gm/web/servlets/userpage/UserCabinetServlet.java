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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            //Creating map with paths for events photo <eventId, pathToEventPhoto>
            Map<Integer,String> eventPhotosPathMap= new HashMap<>();

            for (UserInEvent iter : userInEvents) {
                System.out.println(iter);
                int eventId= iter.getEventId();
                String pathToEventPhoto = photoService.getEventPhoto(eventId).getPath();
                eventPhotosPathMap.put(eventId, pathToEventPhoto);
            }
            request.setAttribute("centralContent","eventsincabinet");
            request.setAttribute("userInEvents", userInEvents);
            request.setAttribute("eventPhotosPathMap", eventPhotosPathMap);

        }

        request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(request, response);
    }
}
