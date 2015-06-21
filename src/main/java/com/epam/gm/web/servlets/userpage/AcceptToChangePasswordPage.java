package com.epam.gm.web.servlets.userpage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.daolayer.ForgotPasswordDao;
import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.ForgotPassword;
import com.epam.gm.model.User;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AcceptToChangePasswordPage extends HttpServlet implements
		HttpRequestHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5854834661058077682L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		HttpSession session = request.getSession();
		String code = request.getParameter("ctcp");
		ForgotPasswordDao forgotPasswordDao = new ForgotPasswordDao();
		if (forgotPasswordDao.isAvailableCode(code)) {
			request.getRequestDispatcher("pages/user/changepasswordpage.jsp")
					.forward(request, response);
			ForgotPassword forgotPassword = forgotPasswordDao
					.getForgotPasswordByCode(code);

			User user = new UserDao().getUserByEmail(forgotPassword.getEmail());
			session.setAttribute("currentForgotPassword", forgotPassword);
			session.setAttribute("currentUser", user);
		} else {
			request.setAttribute("trouble", true);
			request.getRequestDispatcher("pages/user/pagenotexist.jsp")
					.forward(request, response);
		}
	}
}
