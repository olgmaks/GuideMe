package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.CountryDao;
import com.epam.gm.model.Country;
import com.epam.gm.model.Language;
import com.epam.gm.services.LanguageService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AdminCountryRequest implements HttpRequestHandler {
	private static final long serialVersionUID = 1L;

	private CountryDao dao;

	public AdminCountryRequest() {
		dao = new CountryDao();
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		LanguageService ls = new LanguageService();
		String action = request.getParameter("action");
		System.out.println(action);
		// List<C> tagList = new ArrayList<Tag>();
		// Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// response.setContentType("application/json");

		if (action != null) {
			try {
				if (action.equals("list")) {

				} else if (action.equals("add") || action.equals("edit")) {
					Integer pureId = dao.getLastPureId();
					for (Language lang : ls.getLocalizedLangs()) {
						Country country = new Country();
						String name = request.getParameter("langCountry"+ lang.getId());
						country.setName(name);
						country.setPureId(pureId + 1);
						country.setLocalId(lang.getId());
						if (action.equals("add")) {
							dao.save(country);
						} else if (action.equals("edit")) {
							int id = Integer.parseInt(request
									.getParameter("id"));
							Map<String, Object> map = new HashMap<>();
							map.put("name", name);
							dao.updateById(id, map);
						}
					}
				} else if (action.equals("delete")) {
					// Delete record
					if (request.getParameter("id") != null) {
						int id = Integer.parseInt(request.getParameter("id"));
						Map<String, Object> map = new HashMap<>();
						map.put("deleted", 1);
						dao.updateById(id, map);

					}
				}
				request.getRequestDispatcher("admincountry.do").forward(request, response);
			} catch (Exception ex) {
				response.getWriter().print(ex.toString());
			}
		}
	}
}