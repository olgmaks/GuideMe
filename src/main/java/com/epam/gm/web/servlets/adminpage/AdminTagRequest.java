package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.TagDao;
import com.epam.gm.model.Language;
import com.epam.gm.model.Tag;
import com.epam.gm.services.LanguageService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AdminTagRequest implements HttpRequestHandler {
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

	private TagDao dao;

	public AdminTagRequest() {
		dao = new TagDao();
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");

		if (action != null) {
			try {
				if (action.equals("add") || action.equals("edit")) {
					Tag tag = new Tag();
					String name = request.getParameter("name");
					tag.setName(name);
					if (action.equals("add")) {
						// Create new record
						tag.setDeleted(false);
						dao.save(tag);
					} else if (action.equals("edit")) {
						// Update existing record
						int id = Integer.parseInt(request.getParameter("id"));
						Map<String, Object> map = new HashMap<>();
						map.put("name", name);
						dao.update(id, map);
					}
					request.getRequestDispatcher("adminTag.do").forward(
							request, response);
				} else if (action.equals("delete")) {
					// Delete record
					if (request.getParameter("id") != null) {
						int id = Integer.parseInt(request.getParameter("id"));
						Map<String, Object> map = new HashMap<>();
						map.put("deleted", 1);
						dao.update(id, map);
					}
				} else if (action.equals("isPresentName")) {
					Boolean isPresentName = dao.isPresentName(request
							.getParameter("name"));
					if (request.getParameter("name").equals("")) {
						isPresentName = true;
					}
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/json");
					response.getWriter()
							.write(new Gson().toJson(isPresentName));

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
