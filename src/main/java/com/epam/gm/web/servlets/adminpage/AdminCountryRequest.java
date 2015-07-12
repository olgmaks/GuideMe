package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
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

	private CountryDao dao;

	public AdminCountryRequest() {
		dao = new CountryDao();
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		LanguageService ls = new LanguageService();
		String action = request.getParameter("action");
		
		if (action != null) {
			Integer id = null;
			try {
				if (action.equals("list")) {

				} else if (action.equals("add") || action.equals("edit")) {
					Integer pureId = dao.getLastPureId();
					if (!request.getParameter("id").equals("")){
						id = Integer.parseInt(request.getParameter("id"));
					}
					for (Language lang : ls.getLocalizedLangs()) {
						Country country = new Country();
						String name = request.getParameter("langCountry"
								+ lang.getId());
						country.setName(name);
						country.setPureId(pureId + 1);
						country.setLocalId(lang.getId());
						country.setDeleted(false);
						if (action.equals("add")) {
							dao.save(country);
						} else if (action.equals("edit")) {

						
							Map<String, Object> map = new HashMap<>();
							map.put("name", name);
							dao.updateById(id++, map);
						}
					}
				} else if (action.equals("delete")) {
					if (request.getParameter("id") != null) {
						int countryId = Integer.parseInt(request
								.getParameter("id"));
						
						dao.deleteByPureId(dao.getCountryById(countryId).getPureId());

					}
				}
				request.getRequestDispatcher("admincountry.do").forward(
						request, response);
			} catch (Exception ex) {
				ex.printStackTrace();
				response.getWriter().print(ex.toString());
			}
		}
	}
}
