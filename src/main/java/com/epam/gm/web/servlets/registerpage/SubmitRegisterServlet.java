package com.epam.gm.web.servlets.registerpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.Address;
import com.epam.gm.model.City;
import com.epam.gm.model.User;
import com.epam.gm.services.AddressService;
import com.epam.gm.services.CityService;
import com.epam.gm.services.UserService;
import com.epam.gm.util.ValidateHelper;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class SubmitRegisterServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		System.out.println("SubmitRegisterServlet");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        System.out.println(email);
//        System.out.println(password);	
		
		boolean ok = true;
		
		Map<Integer, String> addressMap = new HashMap<>();
		
		
		Map<String, String[]> params = request.getParameterMap();
		for(Map.Entry<String, String[]> entry : params.entrySet() ) { 
			if (entry.getKey() == null) continue;
			
//			System.out.println("key = " + entry.getKey());
//			System.out.println("value = " +  Arrays.toString(entry.getValue())  );
			
			if(entry.getKey().startsWith("addressByLang_")) {
				
				String key = entry.getKey().replace("addressByLang_", "").trim();
				if(ValidateHelper.isNumber(key)) {
					Integer langId = Integer.parseInt(key);
					
					if(entry.getValue() != null && entry.getValue().length > 0) {
						String value = entry.getValue()[0];
						
						String result = ValidateHelper.validateField("address", value, User.class);
						
						System.out.println("**********************");
						System.out.println("param = " + entry.getKey());
						System.out.println("value = " + value);
						System.out.println("result = " + result);
						
						addressMap.put(langId, value);
						
						if(!"".equals(result))
							if(!result.endsWith(".ok"))
								ok = false;						
					}
					
				}
				
				
			} else {
				
				String param = entry.getKey().trim();
				String value = "";
				if(entry.getValue() != null && entry.getValue().length > 0) 
					value = entry.getValue()[0];
				
				String result = ValidateHelper.validateField(param, value, User.class);
				
				System.out.println("**********************");
				System.out.println("param = " + param);
				System.out.println("value = " + value);
				System.out.println("result = " + result);
				
				System.out.println("**********************AdressMap");
				System.out.println(addressMap);
				
				
				if(!"".equals(result))
					if(!result.endsWith(".ok"))
						ok = false;
				
			}
		}	
		
		System.out.println("ok="+ok);
		
		if(ok) {
			
			String firstName = request.getParameter("firstName").trim();
			String lastName = request.getParameter("lastName").trim();
			String email = request.getParameter("email").trim();
			String sex = request.getParameter("sex").trim();
			String cellNumber = request.getParameter("cellNumber").trim();
			String password = request.getParameter("password").trim();
			
			Integer cityId = Integer.parseInt(request.getParameter("cityId").trim());
			
			//get our city
			CityService cityService = new CityService();
			City city = cityService.getCityById(cityId);
			
			Map<Integer, Integer> cityMap = new HashMap<>();
			//get analogs
			List<City> cities = cityService.getCitiesByPureId(city.getPureId());
			for(City c: cities) {
				cityMap.put(c.getLocalId(), c.getId());
			}
			System.out.println("saving adress ================================================================");
			AddressService addressService = new AddressService();
			Integer newPureId = addressService.getLastPureId() + 1;
			
			for(City c: cities) {
				
				Address address = new Address();
				address.setCityId(c.getId());
				address.setLocalId(c.getLocalId());
				address.setPureId(newPureId);
				address.setAddress(addressMap.get(c.getLocalId()));
				
				addressService.save(address);
			}
			
			Address newAddress = addressService.getAddressByPureId(newPureId).get(0);
			
			System.out.println("saving user ================================================================");
			System.out.println("firstName = " + firstName);
			System.out.println("lastName = " + lastName);
			System.out.println("email = " + email);
			System.out.println("sex = " + sex);
			System.out.println("cellNumber = " + cellNumber);
			System.out.println("cityId = " + cityId);
			System.out.println("adressId = " + newAddress.getId());
			
	        User user = new User();
	        user.setFirstName(firstName);
	        user.setLastName(lastName);
	        user.setEmail(email.toLowerCase());
	        user.setUserTypeId(8);
	        user.setSex(sex);
	        user.setLangId(3);
	        user.setCellNumber(cellNumber);
	        user.setIsActive(true);
	        user.setAddressId(newAddress.getId());
	        user.setPassword(password);
	        
	        UserService userService = new UserService();
	        try {
				userService.saveUser(user);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        request.getRequestDispatcher("home.do").forward(request, response);
			
		}
		
	}
	
}
