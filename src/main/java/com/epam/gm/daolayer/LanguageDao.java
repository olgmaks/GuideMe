package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.epam.gm.model.*;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class LanguageDao extends AbstractDao<Language> {

    public LanguageDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), Language.class);
    	super(Language.class);
    }
    
	//gryn
	public List<Language> getLocalizedLangs() throws SQLException {
		List<Language> result = getByField("localized", true);
		return result;      
	}
	
    //gryn - all not deleted langs
	public List<Language> getAllActiveLangs() throws SQLException {
		List<Language> result = getByField("deleted", false);
		//Collections.sort(result);
		
		return result;
	}
	
	public Language getLangById(Integer id) throws SQLException {
		List<Language> result = getByField("id", id);
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}	
	
	public Language getlangByName(String name) throws SQLException {
		List<Language> result = getByField("name", name);
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}
	
	public List<Language> getAllUserLangs(Integer userId) throws SQLException {
		UserLanguageDao dao = new UserLanguageDao();

		List<UserLanguage> list = dao.getByField("user_id", userId);
		
		List<Language> res = new ArrayList<>();
		List<Language> all = getAllActiveLangs();

		for (UserLanguage userLang : list) {
			Language lang = getLangById(userLang.getLangId());

			if (lang == null)
				continue;

			if (all.contains(lang))
				res.add(lang);
		}
		
		return res;
	} 
	
	
	public static void main(String[] args) throws SQLException {
//		List<UserLanguage> list = new UserLanguageDao().getByField("user_id", 14);
//		
//		System.out.println("UserLanguage");
//		list.forEach(System.out::println);
//		
//		
//		List<Language> all = new LanguageDao().getAllActiveLangs();
//		
//		System.out.println("all");
//		all.forEach(x -> System.out.println(x));
		
		
		List<Language> list = new LanguageDao().getAllUserLangs(14);
		list.forEach(System.out::println);
		
	}


}
