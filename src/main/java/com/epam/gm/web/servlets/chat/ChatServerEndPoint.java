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

import com.epam.gm.daolayer.MessageEventDao;
import com.epam.gm.model.MessageEvent;
import com.epam.gm.sessionrepository.SessionRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
 
/**
 * ChatServer
 * @author Jiji_Sasidharan
 */
@ServerEndpoint(value="/chatevent/{room}", configurator=ChatServerEndPointConfigurator.class)
public class ChatServerEndPoint {
    
    private Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());

    /**
     * Callback hook for Connection open events. This method will be invoked when a 
     * client requests for a WebSocket connection.
     * @param userSession the userSession which is opened.
     */
    @OnOpen
    public void onOpen(Session userSession, @PathParam("room") final String room) {
    	System.out.println(userSession.getId());
    	System.out.println("room " + room);
    	userSession.getUserProperties().put("room", room);
        userSessions.add(userSession);
    }
     
    /**
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
        System.out.println("Message Received: " + message);
        String room = (String) userSession.getUserProperties().get("room");
		System.out.println(room);
        for (Session session : userSessions) {
            System.out.println("Sending to " + session.getId());
            if(session.getUserProperties().get("room").equals(room)){
            	session.getAsyncRemote().sendText(message);
            }
        }
        saveMessage(room, message);
        
    }
    public void saveMessage(String room, String messageJSON) {
    
        JsonElement jelement = new JsonParser().parse(messageJSON);
        //System.out.println(jsonLine);
        JsonObject  jobject = jelement.getAsJsonObject();
        String userId = jobject.get("userId").toString().replace("\"", "");
        String message = jobject.get("message").toString();
        message = message.substring(1, message.length()-1);
    	MessageEvent me = new MessageEvent();
        me.setEventId(Integer.parseInt(room));
        me.setSenderId(Integer.parseInt(userId));
        me.setMessage(message);
        MessageEventDao meDao = new MessageEventDao();
        try {
			meDao.save(me);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}