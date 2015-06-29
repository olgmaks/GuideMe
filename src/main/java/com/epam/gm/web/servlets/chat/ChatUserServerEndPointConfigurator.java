package com.epam.gm.web.servlets.chat;

import javax.websocket.server.ServerEndpointConfig.Configurator;


/**
 * ChatServerEndPointConfigurator
 * @author Jiji_Sasidharan
 */
public class ChatUserServerEndPointConfigurator extends Configurator {
 
    private static ChatUserServerEndPoint chatServer = new ChatUserServerEndPoint();
 
    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        return (T)chatServer;
    }
}
