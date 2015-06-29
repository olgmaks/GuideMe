package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.MessageEvent;
import com.epam.gm.model.MessageUser;
import com.epam.gm.model.RatingEvent;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class MessageUserDao extends AbstractDao<MessageUser> {
	private static final String GET_BY_USER_ID = " mu WHERE (mu.sender_id = %S AND mu.user_id = %S) OR "
														+ " (mu.sender_id = %S AND mu.user_id = %S)";
	private static final String UPDATE_READ_MESSAGE = "update message_user set = is_redad = 1 where sender_id = %S and user_id = %S and is_raed = 0";																									
    public MessageUserDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), MessageUser.class);
    	super(MessageUser.class);
    }
    public void save(MessageUser mu) throws IllegalArgumentException, IllegalAccessException, SQLException {
    	super.save(mu);
    }
    
    public List<MessageUser> getByUser(int userId, int senderId) throws SQLException{
    	List<MessageUser> list = super.getWithCustomQuery(String.format(
				GET_BY_USER_ID, senderId, userId, userId, senderId));
    	return list;
    }
   

}
