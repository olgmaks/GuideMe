package com.epam.gm.sessionrepository;

import com.epam.gm.model.Language;
import com.epam.gm.model.Photo;
import com.epam.gm.model.User;
import com.epam.gm.services.UserService;
import com.epam.gm.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class SessionRepository {

    public static final String SESSION_USER = "sessionUser";
    public static final String SESSION_USER_AVATAR = "sessionUserAvatar";
    public static final String SESSION_LANGUAGE = "sessionLanguage";


    public static void setSessionUserAvatar(HttpServletRequest request, Photo avatar) {
        request.setAttribute("sessionUserAvatar", avatar);
    }

    public static User getSessionUser(HttpServletRequest request) {

        HttpSession session = request.getSession(true);

        User sessionUser = (User) session.getAttribute(SESSION_USER);
        System.out.println("session user : " + sessionUser);

        if (sessionUser == null) {
            return null;
        }

        User updatedSessionUser = null;
        try {
            Integer id = sessionUser.getId();
            System.out.println("session user id : " + id);
            updatedSessionUser = UserService.serve().getUserById(id);
            session.setAttribute(SESSION_USER,updatedSessionUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedSessionUser;
    }

    public static void setSessionUser(HttpServletRequest request, User user) {
        request.getSession(true).setAttribute(SESSION_USER, user);
    }

    public static Language getSessionLanguage(HttpServletRequest request) {
        return (Language) request.getSession(true).getAttribute(SESSION_LANGUAGE);
    }

    public static void setSessionLanguage(HttpServletRequest request, Language language) {
        request.getSession(true).setAttribute(SESSION_LANGUAGE, language);
    }

    //gryn
    public static boolean isAdmin(HttpServletRequest request) {

        User user = getSessionUser(request);
        if (user != null)
            return user.getUserTypeId().equals(Constants.USER_TYPE_ADMIN);

        return false;
    }

    public static boolean isUser(HttpServletRequest request) {

        User user = getSessionUser(request);
        if (user != null)
            return user.getUserTypeId().equals(Constants.USER_TYPE_USER);

        return false;
    }

    public static boolean isGuide(HttpServletRequest request) {

        User user = getSessionUser(request);
        if (user != null)
            return user.getUserTypeId().equals(Constants.USER_TYPE_GUIDE);

        return false;
    }

}
