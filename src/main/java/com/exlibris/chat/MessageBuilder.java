package com.exlibris.chat;

public class MessageBuilder {
    private String from;
    private String to;
    private String content;

    public MessageBuilder setFrom(String from) {
        this.from = from;
        return this;
    }

    public MessageBuilder setTo(String to) {
        this.to = to;
        return this;
    }

    public MessageBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public Message createMessage() {
        return new Message(from, to, content);
    }
}