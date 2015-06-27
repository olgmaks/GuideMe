
package com.epam.gm.daolayer;

import com.epam.gm.model.User;
import com.epam.gm.model.UserInEvent;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;
import com.epam.gm.services.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInEventDao extends AbstractDao<UserInEvent> {
	private static final String GET_BY_EVENT_AND_USER = 
			" uie WHERE uie.event_id = ?eventId AND uie.user_id = ?userId";
	
	private static final String GET_BY_EVENT_ONLY_MEMBERS =
			" uie WHERE uie.is_member = TRUE AND uie.event_id = ? ";
	

    public UserInEventDao() {
        //gryn
    	//super(ConnectionManager.getConnection(), UserInEvent.class);
    	super(UserInEvent.class);
    }

    public List<UserInEvent> getAllUsersInEvents() throws SQLException {
        return super.getAll();
    }

    public List<UserInEvent> getEventsByUserId(int userId) throws SQLException {
        return super.getByField("user_id", userId);
    }
    
     public List<UserInEvent> getUsersByEventId(int eventId) throws SQLException {
        return super.getByField("event_id", eventId);
    }
    
     public List<UserInEvent> getByEventAndUser(Integer eventId, Integer userId) throws SQLException {
         return getWithCustomQuery(GET_BY_EVENT_AND_USER.replace("?eventId", eventId.toString())
        		 .replace("?userId", userId.toString()));
     }
     
     public List<UserInEvent> getByEventOnlyMembers(Integer eventId) throws SQLException {
         return getWithCustomQuery(GET_BY_EVENT_ONLY_MEMBERS.replace("?", eventId.toString()));
     }
     
     public List<User> getByEventOnlyMembersToUsers(Integer eventId) throws SQLException {
    	 List<UserInEvent> userInEvent = getByEventOnlyMembers(eventId);
    	 
    	 List<User> res = new ArrayList<User>();
    	 
    	 UserService userService = new UserService();
    	 for(UserInEvent u: userInEvent) {
    		 User user = userService.getUserById(u.getUserId());
    		 res.add(user);
    	 }
    	 
    	 return res;
     }
     
     public static void main(String[] args) throws SQLException {
		//new UserInEventDao().getByEventOnlyMembers(4).forEach(x->System.out.println(x));
	}
  
}
