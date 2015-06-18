package com.epam.gm.web.servlets.usertags;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.model.Language;
import com.epam.gm.model.Tag;
import com.epam.gm.model.User;
import com.epam.gm.model.UserLanguage;
import com.epam.gm.model.UserTag;
import com.epam.gm.services.LanguageService;
import com.epam.gm.services.TagService;
import com.epam.gm.services.UserLanguageService;
import com.epam.gm.services.UserTagService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

public class LangSubmitUserTagsServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {


		System.out.println("LangSubmitUserTagsServlet");
		
		UserLanguageService serv = new UserLanguageService();
		LanguageService langService = new LanguageService();
		User user = SessionRepository.getSessionUser(request);
		System.out.println("User = " + user);

		if (user != null) {
			
			try {
				serv.deleteAllUserLangs(user.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			Map<String, String[]> params = request.getParameterMap();
			for (Map.Entry<String, String[]> entry : params.entrySet()) {
				System.out.println("key:" + entry.getKey());
				System.out.println("val:" + Arrays.toString(entry.getValue()));



				// if("tag[9-a]".equals(entry.getKey())) {
				if (entry.getKey().startsWith("tag[")) {

					for (String str : entry.getValue()) {
						Language lang = langService.getlangByName(str.trim());
						if (lang == null)
							continue;

						UserLanguage temp = new UserLanguage();
						temp.setLangId(lang.getId());
						temp.setUserId(user.getId());

						System.out.println("saving: " + temp);
						try {

							serv.save(temp);
						} catch (IllegalArgumentException
								| IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
			}
		}
		
		
		response.setContentType("application/json");
		Map<String,String> map = new HashMap<>();
		map.put("valid", "Saved !");
		response.getWriter().write(new Gson().toJson(map));			
		
	}

}
