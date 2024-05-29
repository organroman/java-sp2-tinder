package models;

public class Message {
    private final User who;
    private final User whom;
    private final String message;
    private final String date;

    public Message(User who, User whom, String message, String date) {
        this.who = who;
        this.whom = whom;
        this.message = message;
        this.date = date;
    }

    public User getWho() {
        return who;
    }

    public User getWhom() {
        return whom;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }
}
