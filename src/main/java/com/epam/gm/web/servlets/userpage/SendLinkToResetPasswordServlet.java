package com.epam.gm.web.servlets.userpage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.ExecutorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.ForgotPasswordDao;
import com.epam.gm.daolayer.UserDao;
import com.epam.gm.hashpassword.MD5HashPassword;
import com.epam.gm.model.ForgotPassword;
import com.epam.gm.model.User;
import com.epam.gm.util.RandomIntegerOnRange;
import com.epam.gm.util.SendMailTLS;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class SendLinkToResetPasswordServlet extends HttpServlet implements
		HttpRequestHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6730484476601633673L;
	private static ExecutorService executorService;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {

		response.setContentType("text/html");
		String email = request.getParameter("email");
		User u = new UserDao().getUserByEmail(email);
		if (new UserDao().getUserByEmail(email) != null) {
			request.getRequestDispatcher("pages/user/successfulsent.jsp")
					.forward(request, response);
			Integer randomInt = RandomIntegerOnRange.generate(0, 1000000000);
			Integer randomInt2 = RandomIntegerOnRange.generate(0, 1000000000);
			String code = null;
			try {
				code = MD5HashPassword.getHashPassword(randomInt.toString())
						+ MD5HashPassword
								.getHashPassword(randomInt2.toString());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			SendMailTLS
					.sendMessage(
							email,
							"Request to change password",
							"Click the link for change password : \nhttp://localhost:8080/GuideMe/changepassword.do?ctcp="
									+ code
									+ "\n\nNote: Link Available only 1 hour!");
			ForgotPassword forgotPassword = new ForgotPassword();
			forgotPassword.setCode(code);
			forgotPassword.setEmail(email);
			forgotPassword.setIsAvailable(true);
			forgotPassword.setTimestamp(new Timestamp(new Date().getTime()));
			new ForgotPasswordDao().saveForgotPassword(forgotPassword);

		} else {
			request.setAttribute("isWrong", true);
			request.getRequestDispatcher(
					"pages/user/userforgotpasswordpage.jsp").forward(request,
					response);

		}

	}
}
