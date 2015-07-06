package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.epam.gm.model.Language;
import com.epam.gm.model.Tag;
import com.epam.gm.model.User;
import com.epam.gm.model.UserLanguage;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;
import com.epam.gm.util.Constants;

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
	
	public Boolean isPresentName(String name) throws SQLException{
		String select = "SELECT ('%S' IN (SELECT l.name FROM language l))";
		return super.getBoolean(String.format(select, name));
	}
	public Boolean isPresentShortName(String shortName) throws SQLException{
		String select = "SELECT ('%S' IN (SELECT l.short_name FROM language l))";
		return super.getBoolean(String.format(select, shortName));
	}
	
	//gryn
	public List<Language> getUserLangsForLocal(User user) throws SQLException {
		List<Language> result = null;
		if(user == null) {
			result = getByField("id", Constants.EN_LANG_ID);
		} else {
			result = new ArrayList<Language>();
			result.add(user.getLang());
		}
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
	
	public void save(Language lang) throws IllegalArgumentException,
		IllegalAccessException, SQLException {
		super.save(lang);
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
	public void update(int id , Map<String, Object> updates) throws SQLException{
		updateById(id, updates);
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
		
		
		List<Language> list = new LanguageDao().getUserLangsForLocal(null);
		for (Language language : list) {
			System.out.println(list);
		}
		
	}

}
