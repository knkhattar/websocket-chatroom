package com.kk.chatroom;

import javax.websocket.server.ServerEndpointConfig.Configurator;

public class ChatServerEndPointConfig extends Configurator {
 
    private static ChatServerEndPoint chatServer = new ChatServerEndPoint();
 
    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        return (T)chatServer;
    }
}