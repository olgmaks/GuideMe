package com.epam.gm.daolayer;

import com.epam.gm.calculators.EventCalculator;
import com.epam.gm.model.City;
import com.epam.gm.model.Country;
import com.epam.gm.model.Event;
import com.epam.gm.model.FriendUser;
import com.epam.gm.model.User;
import com.epam.gm.model.UserActivity;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
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
    
	private static final String GET_TAGS_BY_EVENTS =
			"SELECT  e.id, e.name, et.tag_id, t.name AS 'tag_name' "
			+ "FROM event e JOIN event_tag et ON e.id = et.event_id "
			+ " JOIN tag t ON et.tag_id = t.id " + " WHERE e.id IN (?) "
			+ " ORDER BY e.id, e.name, t.name ";
    
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
	
	public void buildTagString(List<Event> list) throws SQLException {
		if(list == null || list.isEmpty()) return;
		
		Map<Integer, Event> map = new HashMap<Integer, Event>();
		
		StringJoiner join = new StringJoiner(",");
		for(Event e: list) {
			join.add(e.getId().toString());
		
			map.put(e.getId(), e);
			
			e.setTagString("");
		}
			
		Connection connection = ConnectionManager.getConnection();
		
		StringBuilder sb = new StringBuilder();
		
		System.out.println(GET_TAGS_BY_EVENTS.replace("?", join.toString()));
		
		PreparedStatement stmt = connection.prepareStatement(GET_TAGS_BY_EVENTS.replace("?", join.toString()));
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Event event =  map.get(rs.getInt("id"));

			event.setTagString(sb.append(event.getTagString()).append("#").append(rs.getString("tag_name")).append(" ").toString());
			
			sb.setLength(0);
		}
		rs.close();
		stmt.close();
		ConnectionManager.closeConnection(connection);
		
	}

	public static void main(String[] args) throws SQLException, IllegalAccessException {
		
		EventDao dao = new EventDao();
		List<Event> list = new EventDao().getAllNotDeletedEventsInTheCity(1);
		
		dao.buildTagString(list);
		//list.forEach(x -> System.out.println(x.getName() + "  " +  x.getTagString()));
		
		
		//new EventDao().buildTagString(null);
		
		//new EventDao().getAllActiveNotDeletedGuideEventsInTheCity(1).forEach(x -> System.out.println(x.getId()));
		
		
		//List<Event> list = new EventDao().getAllNotDeletedEventsInTheCity(1);
		//Collections.sort(list, Event.BY_CREATED_DATE);
		
		//list.forEach(x -> System.out.println(x.getName() + "  " +  x.getCreatedOn()));
		
		//new EventDao().getAllNotDeletedEventsByPattern("²×").forEach(x -> System.out.println(x.getName()));
		
	}
	



}
