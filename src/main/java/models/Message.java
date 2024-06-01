package models;

import java.util.Date;

public class Message {
    private final User chatOwner;
    private final User receiver;
    private final String content;
    private final Date date;

    public Message(User chatOwner, User receiver, String content, Date date) {
        this.chatOwner = chatOwner;
        this.receiver = receiver;
        this.content = content;
        this.date = date;
    }

    public User getChatOwner() {
        return chatOwner;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "chatOwnerName=" + chatOwner.getName() + " (ID: " + chatOwner.getId() + "), " +  " (LOGO: " + chatOwner.getImgUrl()  + "), "
                +
                "receiver=" + receiver.getName() + " (ID: " + receiver.getId() + "), " +  " (LOGO: " + receiver.getImgUrl()  + "), "
               +
                "content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
