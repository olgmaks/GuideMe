package com.epam.gm.web.servlets.userpage;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.model.User;
import com.epam.gm.model.Wallet;
import com.epam.gm.services.WalletService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class WalletServlet extends HttpServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("sessionUser");
		Wallet wallet = new Wallet();
		WalletService walletService = new WalletService();
		request.setAttribute("centralContent", "wallet");

		wallet = walletService.getByUserId(user.getId());
	/*	Integer balance = wallet.getBalance();*/
		
		request.setAttribute("wallet", wallet);
/*		request.setAttribute("balance", balance);*/
		request.setAttribute("account", "410013283800566");
		String eventNamePay = "Pay";

		request.setAttribute("eventNamePay",
				URLEncoder.encode(eventNamePay, "UTF8"));

		request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(
				request, response);

	}

}
