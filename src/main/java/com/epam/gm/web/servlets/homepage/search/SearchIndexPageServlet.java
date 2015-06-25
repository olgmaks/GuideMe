package com.epam.gm.web.servlets.homepage.search;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.Event;
import com.epam.gm.model.User;
import com.epam.gm.services.EventService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.util.DataValidator;
import com.epam.gm.util.ValidateHelper;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class SearchIndexPageServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException,
			IllegalAccessException {
		
		Map<String, String> searchProps = new HashMap<String, String>();
		
		StringJoiner statusJoiner = new StringJoiner(",", "(", ")"); 
		statusJoiner.setEmptyValue("('active')");
		
		System.out.println("SearchIndexPageServlet");
		Map<String, String[]> params = request.getParameterMap();
		for(Map.Entry<String, String[]> entry : params.entrySet() ) { 
			if (entry.getKey() == null) continue;
			if(entry.getValue()[0] == null) continue;
			
			if(entry.getValue()[0].trim().length() == 0) continue;
			
			System.out.println("param = " + entry.getKey());
			System.out.println("value = " + entry.getValue()[0]);
				
			String key = entry.getKey();
			String val = entry.getValue()[0].trim();
			
			if("icon_prefix".equals(key) && val.length() > 0) {
				searchProps.put("text", val);
			} else if("cityId".equals(key) && DataValidator.isPositiveNumber(val)) {
				searchProps.put("cityId", val);
			} else if("selCountryId".equals(key) && DataValidator.isPositiveNumber(val)) {
				searchProps.put("countryId", val);
			} else if(key.startsWith("status_") && "on".equals(val)) {
				statusJoiner.add("'" + key.replace("status_", "") + "'") ;
			} else if("moderator_type".equals(key)) {
				searchProps.put("moderator_type", val);
			} else if("max_members".equals(key)) {
				searchProps.put("max_members", val);
			}
			
		}
		
		//System.out.println("statusJoiner:" + statusJoiner.toString());
		//System.out.println("Len:" + statusJoiner.length());
		
		searchProps.put("status", statusJoiner.toString());
			
		
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        
        EventService eventService = new EventService();
        //List<Event> results = eventService.getAllActiveNotDeletedEvents();
        
        User user = SessionRepository.getSessionUser(request);
        
        System.out.println("Search event map:");
        System.out.println(searchProps);
        
        
        List<Event> results = eventService.getBySearchMap(searchProps, user);
        eventService.buildTagString(results);
        		
        Map<String, Object> responseMap = new HashMap<>();

        responseMap.put("isEmpty", results.isEmpty());
        responseMap.put("results", results);
        response.getWriter().write(new Gson().toJson(responseMap));
				
		
		
	}

}
