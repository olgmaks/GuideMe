package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.CityDao;
import com.epam.gm.daolayer.TagDao;
import com.epam.gm.model.City;
import com.epam.gm.model.Tag;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AdminCityRequest implements HttpRequestHandler {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

	private CityDao dao;

	public AdminCityRequest() {
		dao = new CityDao();
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		List<City> cityList = new ArrayList<City>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");

		if (action != null) {
			try {
				if (action.equals("list")) {
					// Fetch Data from Student Table
					cityList = dao.getAll();
					// Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", cityList);
					String jsonArray = gson.toJson(JSONROOT);
					response.setCharacterEncoding("UTF-8");
					response.getWriter().print(jsonArray);
				} else if (action.equals("create") || action.equals("update")) {
					Tag tag = new Tag();
					String name = request.getParameter("name");
					tag.setName(name);
					if (action.equals("create")) {
						// Create new record
						tag.setDeleted(false);
						//dao.save(tag);
					} else if (action.equals("update")) {
						// Update existing record
						int id = Integer.parseInt(request.getParameter("id"));
						Map<String, Object> map = new HashMap<>();
						map.put("name", name);
						//dao.update(id, map);
					}

					// Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Record", tag);

					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);
					response.setCharacterEncoding("UTF-8");
					response.getWriter().print(jsonArray);
				} else if (action.equals("delete")) {
					// Delete record
					if (request.getParameter("id") != null) {
						int id = Integer.parseInt(request
								.getParameter("id"));
						Map<String, Object> map = new HashMap<>();
						map.put("deleted", 1);
						//dao.update(id, map);

						// Return in the format required by jTable plugin
						JSONROOT.put("Result", "OK");

						// Convert Java Object to Json
						String jsonArray = gson.toJson(JSONROOT);
						response.setCharacterEncoding("UTF-8");
						response.getWriter().print(jsonArray);
					}
				}
			} catch (Exception ex) {
				JSONROOT.put("Result", "ERROR");
				JSONROOT.put("Message", ex.getMessage());
				String error = gson.toJson(JSONROOT);
				ex.printStackTrace();
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print(error);
			}
		}
	}
}
