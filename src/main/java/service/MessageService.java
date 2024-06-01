package service;

import dao.MessageDao;
import models.Message;

import java.sql.SQLException;
import java.util.List;

public class MessageService {
    private final MessageDao messageDao;

    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public List<Message> getMessages(int chatOwnerId, int receiverId) throws SQLException {
        return messageDao.select(chatOwnerId, receiverId);
    }

    public void addMessage(Message message) throws SQLException {
        messageDao.insert(message);
    }

}
