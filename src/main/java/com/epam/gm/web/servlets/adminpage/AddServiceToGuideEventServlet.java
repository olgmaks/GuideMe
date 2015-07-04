package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.gm.daolayer.ServiceDao;
import com.epam.gm.model.Service;
import com.epam.gm.model.User;
import com.epam.gm.services.GuideServicesService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class AddServiceToGuideEventServlet extends HttpServlet implements
		HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4717940393391491278L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		System.out.println("************* IN SERVLET");
		Integer select = Integer.parseInt(request.getParameter("select"));
		GuideServicesService serviceService = new GuideServicesService();
		Service service = serviceService.getServiceById(select);
		User user = (User) session.getAttribute("sessionUser");
		response.setContentType("application/json");

		map.put("name", service.getName());
		map.put("description", service.getDescription());
		map.put("price", service.getPrice());
		response.getWriter().write(new Gson().toJson(map));
	}
}
