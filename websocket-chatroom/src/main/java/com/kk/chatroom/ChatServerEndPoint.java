package com.kk.chatroom;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
 
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
 
@ServerEndpoint(value="/chat", configurator=ChatServerEndPointConfig.class)
public class ChatServerEndPoint {
    
    private Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
 
    @OnOpen
    public void onOpen(Session session) {
    	sessions.add(session);
    }
     
    @OnClose
    public void onClose(Session session) {
    	sessions.remove(session);
    }
     
    @OnMessage
    public void onMessage(String message, Session userSession) {
        System.out.println("Message Received: " + message);
        for (Session session : sessions) {
            System.out.println("Sending to " + session.getId());
            session.getAsyncRemote().sendText(message);
        }
    }
}