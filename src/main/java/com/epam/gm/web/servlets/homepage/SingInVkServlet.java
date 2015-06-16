package com.epam.gm.web.servlets.homepage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.User;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class SingInVkServlet extends HttpServlet implements HttpRequestHandler {
	private final String REDIRECT_URI = "http://localhost:8080/GuideMe/loginvk.do";
	private final String APP_ID = "4955136";
	private final String APP_SECRET = "woAtcmEdvhP9Llc4WNnd";
	/**
	 * 
	 */
	private static final long serialVersionUID = -1210732493300728993L;

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException {
		String userEmail = null;
		String accessToken = null;
		String userId = null;
		String code = req.getParameter("code");

		try {
			String getAccessTokenUri = "https://oauth.vk.com/access_token?"
					+ "client_id=" + APP_ID + "&client_secret=" + APP_SECRET
					+ "&code=" + code + "&redirect_uri="
					+ URLEncoder.encode(REDIRECT_URI, "UTF-8");
			URL url = new URL(getAccessTokenUri);
			URLConnection c = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				b.append(inputLine + "\n");
			}

			JSONObject jsonObject = new JSONObject(b.toString());
			accessToken = (String) jsonObject.get("access_token");
			// userEmail = (String) jsonObject.get("email");
			userId = (String) jsonObject.get("user_id").toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String graph = null;
		String doRequestUri = "https://api.vk.com/method/users.get?user_id="
				+ userId + "&v=5.34&access_token=" + accessToken;
		URL u = new URL(doRequestUri);
		URLConnection c = u.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				c.getInputStream()));
		String inputLine;
		StringBuffer b = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			b.append(inputLine + "\n");
		}

		try {

			JSONObject root = new JSONObject(b.toString());
			JSONArray array = root.getJSONArray("response");
			JSONObject jsonObject = array.getJSONObject(0);
			String first_name = (String) jsonObject.get("first_name");
			String first_name_encoded = new String(
					first_name.getBytes("Cp1251"), "UTF-8");

			String last_name = (String) jsonObject.get("last_name");
			String last_name_encoded = new String(last_name.getBytes("Cp1251"),
					"UTF-8");
			UserDao userDao = new UserDao();
			boolean isValid = false;
			Map<String, Object> map = new HashMap<>();
			if (userDao.getUserByVkId(userId) == null) {
				User user = new User();
				user.setVkId(userId.toString());
				user.setFirstName(first_name_encoded);
				user.setLastName(last_name_encoded);
				user.setEmail("");
				user.setUserTypeId(8);
				user.setLangId(3);
				user.setIsActive(true);
				user.setPassword("");
				try {
					userDao.saveUser(user);

				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			} else {
				User user = userDao.getUserByVkId(userId);
				SessionRepository.setSessionUser(req, user);
				isValid = true;
				System.out.println(user.getFirstName() + " " + user.getLastName());
				map.put("userEmail",
						user.getFirstName() + " " + user.getLastName());
				map.put("sessionUser", user);
			}
			res.setContentType("application/json");
			map.put("isValid", isValid);
			res.getWriter().write(new Gson().toJson(map));

			RequestDispatcher requestDispatcher = req
					.getRequestDispatcher("pages/index.jsp");
			requestDispatcher.forward(req, res);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
