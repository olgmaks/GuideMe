package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.CityDao;
import com.epam.gm.model.City;
import com.epam.gm.model.Language;
import com.epam.gm.services.LanguageService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AdminCityRequest implements HttpRequestHandler {
	private CityDao dao;

	public AdminCityRequest() {
		dao = new CityDao();
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		LanguageService ls = new LanguageService();
		String action = request.getParameter("action");
		System.out.println("action = " + action);
		Integer id = null;
		if (action != null) {
			try {
				if (action.equals("add") || action.equals("edit")) {

					Integer pureId = dao.getLastPureId();
					if (!request.getParameter("id").equals("")) {
						id = Integer.parseInt(request.getParameter("id"));
					}
					int i = 0;
					for (Language lang : ls.getLocalizedLangs()) {
						City city = new City();
						String name = request.getParameter("langCity"
								+ lang.getId());
						
						city.setName(name);
						city.setPureId(pureId + 1);
						city.setLocalId(lang.getId());
						city.setDeleted(false);
						if (action.equals("add")) {
							city.setCountryId(Integer.parseInt(request
									.getParameter("country" + lang.getId())));
							dao.save(city);
						} else if (action.equals("edit")) {
							Map<String, Object> map = new HashMap<>();
							map.put("name", name);
							id = dao.getCityByPureLocalized(
									dao.getCityById(id).getPureId()).get(i++)
									.getId();
							dao.updateById(id, map);

						}
					}

				} else if (action.equals("delete")) {
					if (request.getParameter("id") != null) {
						int cityId = Integer.parseInt(request
								.getParameter("id"));
						
						dao.deleteByPureId(dao.getCityById(cityId).getPureId());

					}

				}
				request.getRequestDispatcher("admincity.do").forward(request,
						response);
			} catch (Exception ex) {
				try {
					request.getRequestDispatcher("404.do").forward(request,
							response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ex.printStackTrace();
			}
		}
	}
}
