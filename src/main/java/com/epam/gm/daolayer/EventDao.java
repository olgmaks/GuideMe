package com.epam.gm.daolayer;

import com.epam.gm.model.City;
import com.epam.gm.model.Event;
import com.epam.gm.model.FriendUser;
import com.epam.gm.model.User;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class EventDao extends AbstractDao<Event> {
	
    private static final String GET_ACTIVE_NOT_DELETED = 
            "e WHERE e.deleted = 0 AND e.status = 'active'";
 
    private static final String GET_ACTIVE_NOT_DELETED_AND_USERS = 
            "e JOIN user u ON e.moderator_id = u.id WHERE e.deleted = 0 AND e.status = 'active' AND u.user_type_id = 2";
  
    private static final String GET_ACTIVE_NOT_DELETED_AND_GUIDE = 
            "e JOIN user u ON e.moderator_id = u.id WHERE e.deleted = 0 AND e.status = 'active' AND u.user_type_id = 3";
    
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
	
	//gryn
//	public List<Event> getAllActiveNotDeletedGuideEventsInTheCity(Integer cityId) throws SQLException {
//		CityDao cityDao = new CityDao(); 
//		City city = cityDao.getCityById(cityId);
//		List<City> cities = cityDao.getCitiesByPureId(city.getPureId());
//		
//		StringJoiner join = new StringJoiner(",");
//		for(City c: cities) {
//			join.add(c.getId().toString());
//		}
//		
//		
//        List<Event> results = new ArrayList<>();
//        String sql = GET_ACTIVE_NOT_DELETED_AND_GUIDE + " AND e.address_id IN (" + join.toString() + ")";
//        
//        System.out.println("sql city:");
//        System.out.println(sql);
//        
//        results = getWithCustomQuery(sql);
//        
//        return results;
//	}
	
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

	
	
	
	
	public static void main(String[] args) throws SQLException {
		new EventDao().getAllActiveNotDeletedGuideEventsInTheCity(1).forEach(x -> System.out.println(x.getId()));
	}
	



}
