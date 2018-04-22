package com.exlibris.chat;


import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class OnlineUsers {

    static Map<String,User> onlineUsers = new ConcurrentHashMap<>();
    enum UserType{ SIMPLE,EXPERT}

    static class User{
        Session session;
        String username;
        UserType type;

        User(Session session, String username, UserType type) {
            this.session = session;
            this.username = username;
            this.type = type;
        }

        public Session getSession() {
            return session;
        }

        public String getUsername() {
            return username;
        }

        public UserType getType() {
            return type;
        }
    }


}
