package com.epam.gm.web.servlets.userpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.services.*;
import org.apache.commons.collections.map.HashedMap;

import com.epam.gm.model.Address;
import com.epam.gm.model.City;
import com.epam.gm.model.Country;
import com.epam.gm.model.Language;
import com.epam.gm.model.User;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class EditProfileServlet implements HttpRequestHandler {

	private UserService userService;

	public EditProfileServlet () {
		userService = new UserService();
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {

		System.out.println("EditProfileServlet");
		
		User user = SessionRepository.getSessionUser(request);
		
		if(user == null) {
			response.sendRedirect("401.do");
			return;
		}
		
		SessionRepository.initBundle(request, "locale.register.messages");
		
		request.setAttribute("user", user);
		
		LanguageService languageService = new LanguageService();
		List<Language> languageList = languageService.getLocalizedLangs();
		request.setAttribute("languageList", languageList);
		
		CountryService countryService = new CountryService();
		List<Country> countryList = countryService.getAll();
		request.setAttribute("countryList", countryList);


		//Maks get recommended friends (right panel)
		List<User> recommendedFriends = userService.getUserFriendsOfFriends(user.getId());
		request.setAttribute("recommendedFriends", recommendedFriends);
		
		String fbVkUser = "disabled";
		
		if(user.getFacebookId() != null && user.getFacebookId().trim().length() > 0) {
			fbVkUser = "";
		} else if(user.getVkId() != null && user.getVkId().trim().length() > 0) {
			fbVkUser = "";
		}
		request.setAttribute("fbVkUser", fbVkUser);
		
		//adress in all locals
		AddressService addressServ = new AddressService();
		Address address = user.getAddress();
		
		Map<Integer, Address> addressLangMap = new HashMap<>();
		Map<Integer, City> cityLangMap = new HashMap<>();
		Map<Integer, Country> countryLangMap = new HashMap<>();
		
		if(address != null && address.getPureId() != null) {
			
		   List<Address> allAddress = addressServ.getAddressByPureId(address.getPureId());
		   
		   Integer cityPure = null;
		   Integer countryPure = null;
		   
		   for(Address a: allAddress) {
			   addressLangMap.put(a.getLocalId(), a);
			   
			   try {
				   cityPure = a.getCity().getPureId();
				   countryPure = a.getCity().getCountry().getPureId();
			   } catch (NullPointerException npe) {
				   System.out.println("Null city or adress !!!");
			   }
		   }
		   
		   if(cityPure != null) {
			   List<City> allCities = new CityService().getCitiesByPureId(cityPure);
			   for(City c: allCities) {
				   cityLangMap.put(c.getLocalId(), c);
			   }
		   }
		   
		   if(countryPure != null) {
			   List<Country> allCountries = new CountryService().getCountriesByPureId(countryPure);
			   for(Country c: allCountries) {
				   countryLangMap.put(c.getLocalId(), c);
			   }
		   }
		   
		}
		

		CityService cityServ = new CityService();
		Map<String, String> selectedCountryMap = new HashMap<>();
		Map<String, String> selectedCityMap = new HashMap<>();
		Map<String, String> selectedAddressMap = new HashMap<>();
		
		Map<String, List<City>> initalCities = new HashMap<String, List<City>>();
		
		String currCityId = "";
		
		List<Language> langs = new LanguageService().getLocalizedLangs();
		for(Language l: langs) {
			String currAddressByLang = "";
			String currCityByLang = "choose";
			String currCountryByLang = "choose";
			
			Address tempAddress = addressLangMap.getOrDefault(l.getId(), null);
			if(tempAddress != null && tempAddress.getAddress() != null) {
				currAddressByLang = tempAddress.getAddress().trim();
				
			}
			selectedAddressMap.put(l.getId().toString(), currAddressByLang);
			
			
			
			//******************************************************************************
			Country tempCountry = countryLangMap.getOrDefault(l.getId(), null);
			if(tempCountry != null) {
				currCountryByLang = tempCountry.getId().toString();
			}
			
			selectedCountryMap.put(l.getId().toString(), currCountryByLang); 
			
			
			//******************************************************************************
			City tempCity = cityLangMap.getOrDefault(l.getId(), null);
			if(tempCity != null) {
				currCityByLang = tempCity.getId().toString();
				currCityId = currCityByLang;
				
			}
			selectedCityMap.put(l.getId().toString(), currCityByLang); 
			
			
			
			if(!"choose".equals(currCountryByLang)) {
				initalCities.put(l.getId().toString(),  cityServ.getCitiesByCountryId(Integer.parseInt(currCountryByLang)) );
			}
			
			
			
		}
		
		request.setAttribute("selectedCountryMap", selectedCountryMap);
		request.setAttribute("selectedCityMap", selectedCityMap);
		request.setAttribute("initalCities", initalCities);
		request.setAttribute("selectedAddressMap", selectedAddressMap);
		request.setAttribute("currCityId", currCityId);
		
		String selMale = ""; String selFemale = "";
		
		if("male".equals(user.getSex())) {
			selMale = "selected";
		} else if("female".equals(user.getSex())) {
			selFemale = "selected";
		}
		
		request.setAttribute("selMale", selMale);
		request.setAttribute("selFemale", selFemale);
		
		

//		
//		System.out.println("***************************************************");
//		System.out.println("City map: ");
//		System.out.println(cityLangMap);
//		
		System.out.println("***************************************************");
		System.out.println("Country map: ");
		System.out.println(selectedCountryMap);	
		
		System.out.println("***************************************************");
		System.out.println("Adress map: ");
		System.out.println(selectedAddressMap);		
		
		
		request.setAttribute("centralContent","userCabinetEditProfile");
        request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(request, response);
        
        
	}

}
