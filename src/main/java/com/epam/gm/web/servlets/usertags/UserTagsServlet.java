package com.epam.gm.web.servlets.usertags;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.Language;
import com.epam.gm.model.Photo;
import com.epam.gm.model.Tag;
import com.epam.gm.model.User;
import com.epam.gm.model.UserInEvent;
import com.epam.gm.services.EventService;
import com.epam.gm.services.LanguageService;
import com.epam.gm.services.PhotoService;
import com.epam.gm.services.TagService;
import com.epam.gm.services.UserInEventService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class UserTagsServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		System.out.println("UserTagsServlet");
		
		PhotoService photoService = new PhotoService();

		UserInEventService userInEventService = new UserInEventService();
		TagService tagService = new TagService();
		LanguageService langService = new LanguageService();
        
        User user = SessionRepository.getSessionUser(request);

        if (user != null) {

        	Photo userPhoto = null;
        	try{
        		userPhoto = photoService.getUserPhoto(user.getId());
        	} catch(Exception e) {
        		System.out.println("Handle me, please!");
        	}
            List<UserInEvent> userInEvents = userInEventService.getEventsByUserId(user.getId());

            //Creating map with paths for events photo <eventId, pathToEventPhoto>
            Map<Integer,String> eventPhotosPathMap= new HashMap<>();

            for (UserInEvent iter : userInEvents) {
                int eventId= iter.getEventId();
                String pathToEventPhoto = photoService.getEventPhoto(eventId).getPath();
                eventPhotosPathMap.put(eventId, pathToEventPhoto);
            }

            request.setAttribute("userInEvents", userInEvents);
            request.setAttribute("eventPhotosPathMap", eventPhotosPathMap);


            if (userPhoto != null) {
                request.setAttribute("userPhoto", userPhoto);
            } else {
                Photo photo = new Photo();
                photo.setPath("img/unknownuserphoto.png");
                request.setAttribute("userPhoto", photo);
            }
            
            
            List<Tag> tags = tagService.getAllUserTags(user.getId());
            request.setAttribute("tags", tags);
            
            
            List<Language> langs = langService.getAllUserLangs(user.getId());
            request.setAttribute("langs", langs);
            
            System.out.println("++++++++++++++++++User langs");
            System.out.println(langs);
            
        }
        request.getRequestDispatcher("pages/user/usertags.jsp").forward(request, response);
		
	}

}
