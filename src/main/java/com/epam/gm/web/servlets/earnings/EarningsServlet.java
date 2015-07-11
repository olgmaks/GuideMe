package com.epam.gm.web.servlets.earnings;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.WithdrawMoneyDao;
import com.epam.gm.model.User;
import com.epam.gm.services.EarningsService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class EarningsServlet extends HttpServlet implements HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2786386616036872462L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		User user = new User();
		user = SessionRepository.getSessionUser(request);
		request.setAttribute("centralContent", "earnings");
		EarningsService earningsService = new EarningsService();
		WithdrawMoneyDao withdrawMoneyDao = new WithdrawMoneyDao();
		request.setAttribute("todayEarnings",
				earningsService.getTodayEarningByModeratorId(user.getId()));
		request.setAttribute("yesterdayEarnings",
				earningsService.getYesterdayEarningsByModeratorId(user.getId()));
		request.setAttribute("lastMonthEarnings",
				earningsService.getLastMonthEarningsByModeratorId(user.getId()));
		request.setAttribute("thisMonthEarnings",
				earningsService.getThisMonthEarningsByModeratorId(user.getId()));
		request.setAttribute("allNotWithDrawEarnings",
				earningsService.getAllNotWithDrawEarnings(user.getId()));
		request.setAttribute("allWithdraws",
				withdrawMoneyDao.getAllWithDrawMoneyByUserId(user.getId()));
		request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(
				request, response);

	}
}
