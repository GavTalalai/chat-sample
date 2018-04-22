package com.exlibris.chat;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@ServerEndpoint(value = "/online/{username}/{type}",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class)
public class ExpertsSocketListener {


    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username,@PathParam("type") String type){
        OnlineUsers.onlineUsers.putIfAbsent(username,new OnlineUsers.User(session,username,OnlineUsers.UserType.valueOf(type)));
        System.out.println("Opened");

    }
    @OnMessage
    public void onMessage(Session session,Message message){
        try {
            System.out.println(message.getContent());
            OnlineUsers.onlineUsers.get(message.getTo()).getSession().getBasicRemote().sendObject(
                    new MessageBuilder()
                            .setTo(null)
                            .setFrom(message.getFrom())
                            .setContent(message.getContent()).createMessage()
            );
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }
    @OnClose
    public void onClose(Session session,CloseReason closeReason){
        OnlineUsers.onlineUsers = OnlineUsers.onlineUsers.entrySet()
                .stream()
                .filter(entry -> !entry.getValue().getSession().getId().equals(session.getId()))
                .collect((Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

    }
}
