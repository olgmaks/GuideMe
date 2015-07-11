package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.CityDao;
import com.epam.gm.daolayer.CountryDao;
import com.epam.gm.model.City;
import com.epam.gm.model.Country;
import com.epam.gm.model.Language;
import com.epam.gm.model.User;
import com.epam.gm.services.LanguageService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class AdminCityServlet implements HttpRequestHandler{
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response){
		try{
		User user = new User();
		user = SessionRepository.getSessionUser(request);

		if (user == null) {
			response.sendRedirect("401.do");
			return;
		}
		CityDao dao = 	new CityDao();
		CountryDao countryDao = new CountryDao();
	    LanguageService ls = new LanguageService();
	    Map <Language, List<Country>> langCountry = new HashMap<Language, List<Country>>();
	    
	    
			for(Language lang: ls.getLocalizedLangs()){
				langCountry.put(lang, countryDao.getCountriesByLocalId(lang.getId()));
			}
		
	    request.setAttribute("langCountry", langCountry);
	    
		request.setAttribute("languageList", ls.getLocalizedLangs());
		Map<City, List<City>> map = new HashMap<City, List<City>>();
		List<City> listAll = dao.getCitiesByLocalId(2);
		for(City c : listAll){
			map.put(c, dao.getCityByPureLocalized(c.getPureId()));
		}
		
		request.setAttribute("cityLocal", map);
		request.setAttribute("centralContent", "adminCity");
		request.setAttribute("cityList",new CityDao().getAll());
		request.getRequestDispatcher("pages/admin/adminPanel.jsp").forward(request,
				response);
	    } catch (SQLException | IOException | ServletException e) {

			try {
				response.sendRedirect("401.do");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	    }
}
