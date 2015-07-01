package com.epam.gm.web.servlets.chat;


import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;




import com.epam.gm.daolayer.MessageUserDao;
import com.epam.gm.model.MessageUser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
 
/**
 * ChatServer
 * @author Jiji_Sasidharan
 */
@ServerEndpoint(value="/chatuser/{userId}", configurator=ChatUserServerEndPointConfigurator.class)
public class ChatUserServerEndPoint {
    
    private Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());

    /**
     * Callback hook for Connection open events. This method will be invoked when a 
     * client requests for a WebSocket connection.
     * @param userSession the userSession which is opened.
     */
    @OnOpen
    public void onOpen(Session userSession, @PathParam("userId") final String userId) {
    	System.out.println("new user with id " + userId);
    	userSession.getUserProperties().put("userId", userId.replace("\"", ""));
        userSessions.add(userSession);
    }
     
    /**
     * 
     * Callback hook for Connection close events. This method will be invoked when a
     * client closes a WebSocket connection.
     * @param userSession the userSession which is opened.
     */
    @OnClose
    public void onClose(Session userSession) {
    	System.out.println("close sesion");
        userSessions.remove(userSession);
    }
     
    /**
     * Callback hook for Message Events. This method will be invoked when a client
     * send a message.
     * @param message The text message
     * @param userSession The session of the client
     */
    @OnMessage
    public void onMessage(String message, Session userSession) {
        JsonElement jelement = new JsonParser().parse(message);
        JsonObject  jobject = jelement.getAsJsonObject();
        String userId = jobject.get("userId").toString().replace("\"", "");
        System.out.println(message);
        String friendId = jobject.get("friendId").toString().replace("\"", "");
        System.out.println("message from " + friendId +" to " + userId);
        System.out.println("size sesion" + userSessions.size());
        for (Session session : userSessions) {
        	//            System.out.println("Sending to " + session.getId());
            if(session.getUserProperties().get("userId").equals(friendId)){
            	try {
					new MessageUserDao().updateRead(Integer.parseInt(userId), Integer.parseInt(friendId));
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	session.getAsyncRemote().sendText(message);
            }
        }
        saveMessage(message);
        
    }
    public void saveMessage(String messageJSON) {
    
        JsonElement jelement = new JsonParser().parse(messageJSON);
        JsonObject  jobject = jelement.getAsJsonObject();
        String userId = jobject.get("userId").toString().replace("\"", "");
        String message = jobject.get("message").toString();
        
        String friendId = jobject.get("friendId").toString().replace("\"", "");
        message = message.substring(1, message.length()-1);
    	MessageUser mu = new MessageUser();
        mu.setSenderId(Integer.parseInt(userId));
        mu.setUserId(Integer.parseInt(friendId));
        mu.setMessage(message);
        MessageUserDao muDao = new MessageUserDao();
        try {
			muDao.save(mu);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
