package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.Language;
import com.epam.gm.services.LanguageService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.epam.gm.web.servlets.usertags.LangSubmitUserTagsServlet;
import com.google.gson.Gson;


public class AdminLanguageRequest implements HttpRequestHandler {
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

	private LanguageService dao;

	public AdminLanguageRequest() {
		dao = new LanguageService();
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		// System.out.println(action);
		// Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// response.setContentType("application/json");

		if (action != null) {
			try {
				if (action.equals("add") || action.equals("edit")) {
					Language lang = new Language();
					String name = request.getParameter("name");

					lang.setName(name);
					lang.setKey(request.getParameter("key"));
					lang.setLocalized(Boolean.parseBoolean(request
							.getParameter("localized")));
					if (action.equals("add")) {
						lang.setDeleted(false);
						dao.save(lang);
						request.getRequestDispatcher("adminlanguage.do").forward(
								request, response);
					} else if (action.equals("edit")) {
						int id = Integer.parseInt(request.getParameter("id"));
						Map<String, Object> map = new HashMap<>();
						map.put("short_name", request.getParameter("key"));
						map.put("name", name);
						dao.updateById(id, map);
						request.getRequestDispatcher("adminlanguage.do").forward(
								request, response);
					}
					

				} else if (action.equals("delete")) {
					if (request.getParameter("id") != null) {
						int id = Integer.parseInt(request.getParameter("id"));
						Map<String, Object> map = new HashMap<>();
						Boolean islocalized = dao.getLangById(id).getLocalized();
						if(!islocalized){
							map.put("deleted", 1);
							dao.updateById(id, map);
						}	
					}
				} else if (action.equals("localized")) {
					if (request.getParameter("id") != null) {
						int id = Integer.parseInt(request.getParameter("id"));
						Language lang = dao.getLangById(id);
						boolean isLocal = lang.getLocalized();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("localized", !isLocal);
						dao.updateById(id, map);
					}
				} else if (action.equals("isPresentName")) {
					Boolean isPresentName = dao.isPresentName(request
							.getParameter("name"));
					if(request.getParameter("name").equals("")){
						isPresentName = true;
					}
					response.setCharacterEncoding("UTF-8");
			        response.setContentType("application/json");
					response.getWriter().write(new Gson().toJson(isPresentName));

				} else if (action.equals("isPresentShortName")) {				
					Boolean isPresentShortName = dao.isPresentShortName(request
							.getParameter("key"));
					if(request.getParameter("key").equals("")){
						isPresentShortName = true;
					}
					response.setCharacterEncoding("UTF-8");
			        response.setContentType("application/json");
					response.getWriter().write(new Gson().toJson(isPresentShortName));
				}
			
			} catch (Exception ex) {
				response.getWriter().write(
						"<script>alert(" + ex.toString() + ")</script>");
			}
		}

	}

}
