package com.epam.gm.web.servlets.servicesinevent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class BuyServicesServlet extends HttpServlet implements
		HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3055241227657527128L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		HttpSession session = request.getSession();
		System.out.println("*****In SERVLET");
		System.out.println(request.getParameter("boughtArrval"));
		String boughtServices = request.getParameter("boughtArrval");
		boughtServices = boughtServices.replaceAll("'", "\"");
		String[] ar = boughtServices.split("\\[|\\]|\\,|\"");
		for (int i = 0; i < ar.length; i++) {
			/* System.out.println(ar[i]); */
			try {
				int boughtId = Integer.parseInt(ar[i]);
				System.out.println(boughtId);
			} catch (NumberFormatException e) {
			}
		}
		session.setAttribute("generalPrice", 22222);
	}
}
