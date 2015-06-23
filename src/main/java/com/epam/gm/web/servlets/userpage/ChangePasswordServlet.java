package com.epam.gm.web.servlets.userpage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.daolayer.ForgotPasswordDao;
import com.epam.gm.daolayer.UserDao;
import com.epam.gm.hashpassword.MD5HashPassword;
import com.epam.gm.model.ForgotPassword;
import com.epam.gm.model.User;
import com.epam.gm.utf8uncoder.StringHelper;
import com.epam.gm.util.StringChecker;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class ChangePasswordServlet extends HttpServlet implements
		HttpRequestHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5039180753657448522L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("currentUser");
		ForgotPassword forgotPassword = (ForgotPassword) session
				.getAttribute("currentForgotPassword");
		String password = request.getParameter("password1");
		String repeatedPassword = request.getParameter("repeatedpassword1");

		System.out.println(password + "\n" + repeatedPassword);
		if (password.equals(repeatedPassword) && password.length() >= 4
				&& StringChecker.checkOnOnlyAlphabetsAndNumeric(password)) {
			request.getRequestDispatcher("pages/user/successfulchanged.jsp")
					.forward(request, response);
			try {
				new UserDao()
						.updateUserPasswordById(
								u.getId(),
								MD5HashPassword.getHashPassword(password,
										u.getEmail()));
				new ForgotPasswordDao().updateAvailability(
						forgotPassword.getId(), false);
			} catch (NoSuchAlgorithmException e) {

				e.printStackTrace();
			}
		}

		else {
			request.setAttribute("trouble", true);
			request.getRequestDispatcher("pages/user/changepasswordpage.jsp")
					.forward(request, response);
		}
	}
}
