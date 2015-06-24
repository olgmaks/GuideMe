package com.epam.gm.util;

import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.User;
import com.epam.gm.services.UserService;
import com.epam.gm.sessionrepository.SessionRepository;

public class CookieUtil {
	public static void saveLastUser(HttpServletResponse response, User user) {
		if (user == null)
			return;

		Cookie cookie = new Cookie("lastUserId", user.getId().toString());
		cookie.setMaxAge(30 * 24 * 60 * 60); // 30 days.
		response.addCookie(cookie);
	}

	public static Integer getLastCountryId(HttpServletRequest request) throws SQLException {
		Integer countryId = Constants.DEFAULT_COUNTRY_ID;
		
		User user = SessionRepository.getSessionUser(request);
		if(user != null) {
			if(user.getAddress() != null && user.getAddress().getCity() != null
					&& user.getAddress().getCity().getCountryId() != null)
				
				countryId = user.getAddress().getCity().getCountryId();
			
			return countryId; 
		}
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie c : cookies) {
				if ("lastUserId".equals(c.getName())) {
					if(DataValidator.isPositiveNumber(c.getValue())) {
						Integer userId = Integer.parseInt(c.getValue());
						user = new UserService().getUserById(userId);
						if(user != null) {
							if(user.getAddress() != null && user.getAddress().getCity() != null
									&& user.getAddress().getCity().getCountryId() != null)
								
								countryId = user.getAddress().getCity().getCountryId();
								
						}
					}
					
					break;
				}
			}

		return countryId;
	}

}
