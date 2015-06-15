package com.epam.gm.web.servlets.homepage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.User;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class SignInFacebookServlet extends HttpServlet implements
		HttpRequestHandler {

	private static final long serialVersionUID = -3125745730541824547L;

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException {
		String code = req.getParameter("code");
		if (code == null || code.equals("")) {
			// an error occurred, handle this
		}

		String token = null;
		try {
			String g = "https://graph.facebook.com/oauth/access_token?client_id=1638969026318216&redirect_uri="
					+ URLEncoder
							.encode("http://localhost:8080/GuideMe/loginfb.do",
									"UTF-8")
					+ "&client_secret=e9b4bce7c3f903cd18c35ed4bbca6d6e&code="
					+ code;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			token = b.toString();
			if (token.startsWith("{"))
				throw new Exception("error on requesting token: " + token
						+ " with code: " + code);
		} catch (Exception e) {
			// an error occurred, handle this
		}

		String graph = null;
		try {
			String g = "https://graph.facebook.com/me?" + token;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			System.out.println("https://graph.facebook.com/picture");
			graph = b.toString();
		} catch (Exception e) {
			// an error occurred, handle this
		}

		String facebookId;
		String firstName = null;
		String middleNames;
		String lastName;
		String email;

		try {
			System.out.println(graph);
			JSONObject json = new JSONObject(graph);
			facebookId = json.getString("id");
			firstName = (String) json.get("first_name");
			lastName = (String) json.get("last_name");
			email = (String) json.get("email");
			UserDao userDao = new UserDao();
			if (userDao.getUserByFacebookId(facebookId) == null) {
				User user = new User();
				user.setFacebookId(facebookId);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				user.setAddressId(1);
				user.setUserTypeId(2);
				user.setLangId(2);
				user.setIsActive(true);
				user.setPassword("");
				try {
					userDao.saveUser(user);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Id " + facebookId);
			System.out.println("name " + firstName);

			System.out.println("email " + email);
			System.out.println("name " + lastName);

			RequestDispatcher requestDispatcher = req
					.getRequestDispatcher("pages/index.jsp");
			requestDispatcher.forward(req, res);
		} catch (JSONException e) {

		}

	}

}
