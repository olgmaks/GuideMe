package com.epam.gm.web.servlets.homepage;

import com.epam.gm.daolayer.ServiceDao;
import com.epam.gm.daolayer.UserLoginingDao;
import com.epam.gm.hashpassword.MD5HashPassword;
import com.epam.gm.model.Language;
import com.epam.gm.model.Service;
import com.epam.gm.model.User;
import com.epam.gm.services.LanguageService;
import com.epam.gm.services.UserService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.util.CookieUtil;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginServlet extends HttpServlet implements HttpRequestHandler {

	private static final long serialVersionUID = 1L;
	private UserService userService;

	public LoginServlet() {
		userService = new UserService();
	}

	private static Logger log = Logger.getLogger(LoginServlet.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = userService.getUserByEmail(email);

		

		System.out.println(email);
		System.out.println(password);
		System.out.println("ajax query has been parsed !");

		Map<String, Object> map = new HashMap<>();
		boolean isValid = false;
		log.warn("good");
		if (user != null) {
			try {
				if (user.getPassword().equals(
						MD5HashPassword.getHashPassword(password,
								user.getEmail()))  && (user.getIsActive()) 
						&& (user.getUserTypeId().equals(1) || user.getUserTypeId().equals(2) || user.getUserTypeId().equals(3))		
						
						
						) {
					System.out.println("logination has been successful");
					SessionRepository.setSessionUser(request, user);

					// and setting lang
					// gryn
					Language lang = new LanguageService().getLangById(user
							.getLangId());
					SessionRepository.setSessionLanguage(request, lang);
					// and cookie
					CookieUtil.saveLastLanguage(response, lang);

					// gryn
					CookieUtil.saveLastUser(response, user);
					System.out.println("**************saved cookie = "
							+ user.getEmail());

					// save login history
					new UserLoginingDao().save(user.getId());

					isValid = true;
					map.put("userEmail", user.getEmail());
					map.put("sessionUser", user);

				} else {
					isValid = false;
				}
			} catch (NoSuchAlgorithmException e) {

				e.printStackTrace();
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		map.put("isValid", isValid);
		response.getWriter().write(new Gson().toJson(map));
		// System.out.println(SessionRepository.getSessionUser(request));
		/*
		 * RequestDispatcher requestDispatcher = request
		 * .getRequestDispatcher("home.do"); requestDispatcher.forward(request,
		 * response);
		 */

	}

}
