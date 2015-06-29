package com.epam.gm.web.servlets.chat;

import javax.websocket.server.ServerEndpointConfig.Configurator;


/**
 * ChatServerEndPointConfigurator
 * @author Jiji_Sasidharan
 */
public class ChatServerEndPointConfigurator extends Configurator {
 
    private static ChatServerEndPoint chatServer = new ChatServerEndPoint();
 
    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        return (T)chatServer;
    }
}