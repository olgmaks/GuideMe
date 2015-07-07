package com.epam.gm.sessionrepository;

import com.epam.gm.model.Language;
import com.epam.gm.model.Photo;
import com.epam.gm.model.User;
import com.epam.gm.services.UserService;
import com.epam.gm.util.Constants;
import com.epam.gm.util.CookieUtil;
import com.epam.gm.web.servlets.local.ShowLocaleTag;

import javax.naming.ldap.Control;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public class SessionRepository {
	
	private static ResourceBundle resBundle;
	private static Map<String, String> bundleHolder;

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
    
    //gryn
    @SuppressWarnings("unchecked")
	public static void initBundle(HttpServletRequest request, String baseName) throws SQLException {
    	bundleHolder = new HashMap<String, String>();
    	List<Language> applicationLangs = null;
    	
    	try {
			applicationLangs = (List<Language>) request.getServletContext()
					.getAttribute("applicationLangs");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(applicationLangs == null || applicationLangs.size() == 0) return;
		
		Language language = SessionRepository
				.getSessionLanguage(request);

		if (language == null) {
			language = CookieUtil.getLastLanguageFromCookie(request);

		}

		if (language != null) {
			SessionRepository.setSessionLanguage(request, language);
		} else {
			SessionRepository.setSessionLanguage(request,
					applicationLangs.get(0));
		}

//		String key = SessionRepository.getSessionLanguage(request)
//				.getKey();
		String locale = SessionRepository.getSessionLanguage(request)
				.getLocale();
		
		
		//Doesn't work
		
		//System.out.println("Locale from db in dervlet: " + locale);
		//resBundle = ResourceBundle.getBundle(baseName, new Locale(locale));
		//resBundle =  ResourceBundle.getBundle(baseName, new Locale(locale), ShowLocaleTag.class.getClassLoader());
    	
		String bundlePath = baseName.replaceAll("\\.", "/") + "_" + locale
				+ ".properties";		
		java.net.URL url = SessionRepository.class.getClassLoader().getResource(bundlePath);

		FileInputStream proStr = null;
		Properties props = new Properties();
		if (url != null) {

			String propsFile = url.getPath();


			try {
				proStr = new FileInputStream(propsFile);
				props.load(proStr);
				Enumeration<?> enKeys = props.propertyNames();
				while (enKeys.hasMoreElements()) {
					String key = (String) enKeys.nextElement();
					String name = props.getProperty(key);

					if (key == null || name == null)
						continue;

					bundleHolder.put(key, name);

				}

			} catch (Exception e) {
				e.printStackTrace();
				;
			} finally {
				try {
					proStr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("NO BUNDLE !!!!");
		}
		
		
    }
    
    public static String getLocaleMessage(String key) {
    	if(bundleHolder == null) return key;
    	
    	return bundleHolder.getOrDefault(key, key);
    }
    
    

}
