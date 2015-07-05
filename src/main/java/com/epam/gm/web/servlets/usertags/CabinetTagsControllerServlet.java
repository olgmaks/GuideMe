package com.epam.gm.web.servlets.usertags;

import com.epam.gm.model.Language;
import com.epam.gm.model.Tag;
import com.epam.gm.model.UserLanguage;
import com.epam.gm.model.UserTag;
import com.epam.gm.services.LanguageService;
import com.epam.gm.services.TagService;
import com.epam.gm.services.UserLanguageService;
import com.epam.gm.services.UserTagService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by OLEG on 03.07.2015.
 */
public class CabinetTagsControllerServlet implements HttpRequestHandler {

    private TagService tagService;
    private UserTagService userTagService;
    private LanguageService languageService;
    private UserLanguageService userLanguageService;

    public CabinetTagsControllerServlet() {
        tagService = new TagService();
        userTagService = new UserTagService();
        languageService = new LanguageService();
        userLanguageService = new UserLanguageService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException {

        String command = request.getParameter("command");

        //Interests commands
        String deleteUserTagCommand = "deleteUserInterestTag";
        String addUserTagCommand = "addUserInterestTag";
        String getAllUserTagsCommand = "getAllUserTags";

        //Languages commands
        String getAllUserLanguagesCommand = "getAllUserLanguages";
        String addUserLanguageCommand = "addUserLanguage";
        String deleteUserLanguageCommand = "deleteUserLanguage";
        String getAllActiveLanguagesCommand = "getAllActiveLanguages";

        Integer sessionUserId = SessionRepository.getSessionUser(request).getId();

        //ajax delete user tag interest
        if (command.equals(deleteUserTagCommand)) {
            String value = request.getParameter("value");

            if (userTagService.isUserHaveTag(value, sessionUserId)) {
                System.out.println("DELETE USER TAG : " + value);
                userTagService.deleteUserTag(sessionUserId, tagService.getTagByName(value).getId());
            }

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson("DELETE TAG - respond from server : " + value));
        }

        //ajax add user tag interest
        if (command.equals(addUserTagCommand)) {
            String value = request.getParameter("value");

            if (!userTagService.isUserHaveTag(value, sessionUserId)) {
                System.out.println("ADD USER TAG : " + value);
                UserTag userTag = new UserTag();
                userTag.setUserId(sessionUserId);
                userTag.setTagId(tagService.getTagByName(value).getId());
                userTagService.save(userTag);
            }

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson("ADD TAG - respond from server : " + value));
        }

        //ajax get all user tags interests
        if (command.equals(getAllUserTagsCommand)) {
            List<Tag> tags = tagService.getAllUserTags(sessionUserId);
            System.out.println("GET ALL USER TAGS : " + tags);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(tags));
        }


        // ajax get all user languages
        if(command.equals(getAllUserLanguagesCommand)){

            List<Language> languages =  languageService.getAllUserLangs(sessionUserId);

            System.out.println("GET ALL USER LANGUAGES : "  + languages);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(languages));
        }


        //ajax add user language command
        if(command.equals(addUserLanguageCommand)){

            String value = request.getParameter("value");

            //will add user language only in case if that language does not not yet present
            if (!userLanguageService.isUserLanguage(sessionUserId, value)){
                System.out.println("ADD USER LANGUAGE : " + value);
                UserLanguage userLanguage = new UserLanguage();
                userLanguage.setUserId(sessionUserId);
                userLanguage.setLangId(languageService.getlangByName(value).getId());
                userLanguageService.save(userLanguage);
            }

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson("ADD TAG - respond from server : " + value));
        }



        //ajax delete user language command
        if(command.equals(deleteUserLanguageCommand)){

            String value = request.getParameter("value");

            //will delete user language only in case if that language already present
            if (userLanguageService.isUserLanguage(sessionUserId,value)) {
                System.out.println("DELETE USER LANGUAGE : " + value);
                userLanguageService.deleteUserLanguage(sessionUserId,value);
            }

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson("DELETE TAG - respond from server : " + value));
        }

        if (command.equals(getAllActiveLanguagesCommand)) {
            List<Language> languages = languageService.getAllActiveLangs();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(languages));
        }
    }

}
