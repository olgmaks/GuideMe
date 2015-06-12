package com.epam.gm.sessionrepository;

import javax.servlet.http.HttpServletRequest;

import com.epam.gm.model.Language;
import com.epam.gm.model.User;

public class SessionRepository {

    public static final String SESSION_USER = "sessionUser";
    public static final String SESSION_LANGUAGE = "sessionLanguage";

    public static User getSessionUser(HttpServletRequest request) {
	return (User) request.getSession(true).getAttribute(SESSION_USER);
    }

    public static void setSessionUser(HttpServletRequest request, User user) {
	request.getSession(true).setAttribute(SESSION_USER, user);
    }

    public static Language getSessionLanguage(HttpServletRequest request) {
	return (Language) request.getSession(true).getAttribute(SESSION_LANGUAGE);
    }

    public static void setSessionLanguage(HttpServletRequest request,Language language) {
	request.getSession(true).setAttribute(SESSION_LANGUAGE, language);
    }

}