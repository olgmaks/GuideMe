package com.epam.gm.web.servlets.earnings;

import java.io.IOException;
import java.net.HttpRetryException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.WithdrawMoneyDao;
import com.epam.gm.model.User;
import com.epam.gm.model.WithdrawMoney;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class MakeWithDrawSerlvet extends HttpServlet implements
		HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8771680141197186353L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		System.out.println("WithDraWWW");
		User user = new User();
		user = SessionRepository.getSessionUser(request);
		Double amountOfMoney = Double.parseDouble(request
				.getParameter("amountofmoneyval"));
		WithdrawMoney withdrawMoney = new WithdrawMoney();
		withdrawMoney.setMoneyAmount(amountOfMoney);
		withdrawMoney.setUserId(user.getId());
		new WithdrawMoneyDao().saveWithDrawMoney(withdrawMoney);

	}
}
