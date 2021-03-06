package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.epam.gm.daolayer.CountryDao;
import com.epam.gm.model.Country;
import com.epam.gm.model.User;
import com.epam.gm.services.LanguageService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AdminCountryServlet implements HttpRequestHandler{
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		User user = new User();
		user = SessionRepository.getSessionUser(request);

		if (user == null) {
			response.sendRedirect("401.do");
			return;
		}
		request.setAttribute("isAdmin", SessionRepository.isAdmin(request));
	    CountryDao dao = 	new CountryDao();
	    LanguageService ls = new LanguageService();
	    
		request.setAttribute("languageList", ls.getLocalizedLangs());
		Map<Country, List<Country>> map = new HashMap<Country, List<Country>>();
		List<Country> listAll = dao.getCountriesByLocalId(2);
		for(Country c : listAll){
			map.put(c, dao.getCountryByPureLocalized(c.getPureId()));
		}
		request.setAttribute("countryLocal", map);
		
		request.setAttribute("centralContent", "adminCountry");
		request.setAttribute("countryList", dao.getAll());
		request.getRequestDispatcher("pages/admin/adminPanel.jsp").forward(request,
				response);
	}

}
