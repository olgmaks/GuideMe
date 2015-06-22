package com.epam.gm.daolayer;

import com.epam.gm.calculators.EventCalculator;
import com.epam.gm.model.City;
import com.epam.gm.model.Country;
import com.epam.gm.model.Event;
import com.epam.gm.model.FriendUser;
import com.epam.gm.model.User;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class EventDao extends AbstractDao<Event> {
	
    private static final String GET_ACTIVE_NOT_DELETED = 
            "e WHERE e.deleted = 0 AND e.status = 'active'";
 
    private static final String GET_ACTIVE_NOT_DELETED_AND_USERS = 
            "e JOIN user u ON e.moderator_id = u.id WHERE e.deleted = 0 AND e.status = 'active' AND u.user_type_id = 2";
  
    private static final String GET_ACTIVE_NOT_DELETED_AND_GUIDE = 
            "e JOIN user u ON e.moderator_id = u.id WHERE e.deleted = 0 AND e.status = 'active' AND u.user_type_id = 3";

    private static final String GET_NOT_DELETED_BY_PATTERN = 
    	    "e WHERE (e.name LIKE '%TEXT%' OR e.name RLIKE '%TEXT%' OR e.description LIKE '%TEXT%' OR e.description RLIKE '%TEXT%') AND e.deleted = FALSE";
    
    

    
	public EventDao() {
		// gryn
		// super(ConnectionManager.getConnection(), Event.class);
		super(Event.class);
	}

	public void saveEvent(Event event) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		super.save(event);
	}

	public List<Event> getAllEvents() throws SQLException {
		return super.getAll();
	}

	public void deleteById(int eventId) throws IllegalAccessException,
			SQLException {
		deleteByField("id", eventId);
	}
	
	//gryn
	public List<Event> getAllActiveNotDeletedEvents() throws SQLException {
		
        List<Event> results = new ArrayList<>();
        String sql = GET_ACTIVE_NOT_DELETED;
        results = getWithCustomQuery(sql);
        
        return results;
	}
	
	//gryn
	public List<Event> getAllActiveNotDeletedUserEvents() throws SQLException {
		
        List<Event> results = new ArrayList<>();
        String sql = GET_ACTIVE_NOT_DELETED_AND_USERS;
        results = getWithCustomQuery(sql);
        
        return results;
	}	
	
	//gryn
	public List<Event> getAllActiveNotDeletedGuideEvents() throws SQLException {
		
        List<Event> results = new ArrayList<>();
        String sql = GET_ACTIVE_NOT_DELETED_AND_GUIDE;
        results = getWithCustomQuery(sql);
        
        return results;
	}		
	
	public List<Event> getAllActiveNotDeletedGuideEventsInTheCity(Integer cityId) throws SQLException {
		CityDao cityDao = new CityDao(); 
		City city = cityDao.getCityById(cityId);
		List<City> cities = cityDao.getCitiesByPureId(city.getPureId());
		
		//StringJoiner join = new StringJoiner(",");
		
		List<Integer> cityIdList = new ArrayList<>();
		for(City c: cities) {
			//join.add(c.getId().toString());
			cityIdList.add(c.getId());
		}
		
        String sql = GET_ACTIVE_NOT_DELETED_AND_GUIDE;
        List<Event> results = getWithCustomQuery(sql);
       
        List<Event> events = new ArrayList<Event>();
        
        for(Event r: results) {
        	if(r.getAddress() != null && r.getAddress().getCity() != null)
        		if(cityIdList.contains(r.getAddress().getCity().getId()))
        			events.add(r);
        
        }
        
        return events;
	}		
	
	public List<Event> getAllNotDeletedEventsInTheCity(Integer cityId, List<Event> entry) throws SQLException {
		CityDao cityDao = new CityDao(); 
		City city = cityDao.getCityById(cityId);
		List<City> cities = cityDao.getCitiesByPureId(city.getPureId());
		
		
		List<Integer> cityIdList = new ArrayList<>();
		for(City c: cities) {
			//join.add(c.getId().toString());
			cityIdList.add(c.getId());
		}
		
       
		List<Event> results = null;
		if(entry == null)
			results = getAllNotDeletedEvents();
		else 
			results = new ArrayList<>(entry);
       
        
        List<Event> events = new ArrayList<Event>();
        
        for(Event r: results) {
        	if(r.getAddress() != null && r.getAddress().getCity() != null)
        		if(cityIdList.contains(r.getAddress().getCity().getId()))
        			events.add(r);
        
        }
        
        return events;
	}
	
	public List<Event> getAllNotDeletedEventsInTheCity(Integer cityId) throws SQLException {
		return getAllNotDeletedEventsInTheCity(cityId, null);
	}
	
	public List<Event> getAllNotDeletedEventsInTheCountry(Integer countryId, List<Event> entry) throws SQLException {
		CountryDao countryDao = new CountryDao(); 
		Country country = countryDao.getCountryById(countryId);
		List<Country> countries = countryDao.getCountriesByPureId(country.getPureId());
		
		
		List<Integer> countryIdList = new ArrayList<>();
		for(Country c: countries) {
			//join.add(c.getId().toString());
			countryIdList.add(c.getId());
		}
		
       
		List<Event> results = null;
		
		if(entry == null)
			results = getAllNotDeletedEvents();
		else
			results = new ArrayList<Event>(entry);
       
        List<Event> events = new ArrayList<Event>();
        
        for(Event r: results) {
        	if(r.getAddress() != null && r.getAddress().getCity() != null && r.getAddress().getCity().getCountryId() != null)
        		if(countryIdList.contains(r.getAddress().getCity().getCountryId()))
        			events.add(r);
        
        }
        
        return events;
	}
	
	public List<Event> getAllNotDeletedEventsInTheCountry(Integer countryId) throws SQLException {
		return getAllNotDeletedEventsInTheCountry(countryId, null);
	}	
	
	
	
	public List<Event> getAllNotDeletedEvents() throws SQLException {
		List<Event> res = getByField("deleted", false);
		return res;
	}
	
	public List<Event> getBySearchMap(Map<String, String> map, User user) throws SQLException {
		List<Event> res = null;
		
		if(map.isEmpty()) {
			res = getAllNotDeletedEvents();
		} else {
			if(map.keySet().contains("text")) {
				res =  getAllNotDeletedEventsByPattern(map.get("text"));
			} else {
				res = getAllNotDeletedEvents();
			}
			
			if(map.keySet().contains("cityId")) {
				res = getAllNotDeletedEventsInTheCity(Integer.parseInt(map.get("cityId")) , res);
			} else if(map.keySet().contains("countryId")) {
					res = getAllNotDeletedEventsInTheCountry(Integer.parseInt(map.get("countryId")), res);
			}
		}
		
		EventCalculator.sortEventsByPoints(res, user == null ? null : user.getId());
		
		return res;
	}
	
	public List<Event> getAllNotDeletedEventsByPattern(String text) throws SQLException {
		
        List<Event> results = new ArrayList<>();
        String sql = GET_NOT_DELETED_BY_PATTERN.replaceAll("TEXT", text);
        results = getWithCustomQuery(sql);
        
        return results;
	}
	
	public static void main(String[] args) throws SQLException {
		//new EventDao().getAllActiveNotDeletedGuideEventsInTheCity(1).forEach(x -> System.out.println(x.getId()));
		
		
		//new EventDao().getAllNotDeletedEventsInTheCity(1).forEach(x -> System.out.println(x.getName()));
		
		new EventDao().getAllNotDeletedEventsByPattern("²×").forEach(x -> System.out.println(x.getName()));
		
	}
	



}
