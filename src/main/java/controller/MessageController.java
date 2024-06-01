package controller;

import collection.CollectionMessagesDao;
import models.Message;
import service.MessageService;

import java.sql.SQLException;
import java.util.List;

public class MessageController {

    private final MessageService messageService = new MessageService(new CollectionMessagesDao());


    public List<Message> getMessages(int chatOwnerId, int receiverId) throws SQLException {
        return messageService.getMessages(chatOwnerId, receiverId);
    }

    public void addMessage(Message message) throws  SQLException {
        messageService.addMessage(message);
    }
}
