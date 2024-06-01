package collection;

import dao.Conn;

import dao.MessageDao;
import models.Message;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CollectionMessagesDao implements MessageDao {
//    private List<Message> messages; todo: delete

    private final static String GET_MESSAGES = """
            select m.chat_owner_id,
                   u1.name as chat_owner_name,
                   u1.logo as chat_owner_logo,
                   m.receiver_id,
                   u2.name as receiver_name,
                   u2.logo as receiver_logo,
                   m.content,
                   m.date
            from messages m
                     join users u1 on chat_owner_id = user_id
                     join users u2 on receiver_id = u2.user_id
            where (chat_owner_id=? and receiver_id=?)
               or (chat_owner_id=? and receiver_id=?);
            """;
    private final String INSERT_MESSAGE = """
            insert into messages (chat_owner_id, receiver_id, content, date)
            values (?, ?, ?, ?);
            """;
    //TODO: DELETE COMMENTED CODE
//    public CollectionMessagesDao(List<Message> messages) {
//        this.messages = messages;
//    }
    @Override
    public void insert(Message message) throws SQLException {
    try (Connection connector = Conn.mcConn()) {
        PreparedStatement ps = connector.prepareStatement(INSERT_MESSAGE);
        ps.setInt(1, message.getChatOwner().getId());
        ps.setInt(2,message.getReceiver().getId());
        ps.setString(3, message.getContent());
        ps.setTimestamp(4, new java.sql.Timestamp(message.getDate().getTime()));
        ps.executeUpdate();
    }
    }

    @Override
    public void update(Message obj) throws SQLException {

    }

    @Override
    public void delete(Message obj) throws SQLException {

    }

    @Override
    public List<Message> select() throws SQLException {
        return null;
    }

    public List<Message> select(int chat_Owner_id, int receiverId) throws SQLException {
        List<Message> messages = new ArrayList<>();
        try (Connection connector = Conn.mcConn()) {
            PreparedStatement ps = connector.prepareStatement(GET_MESSAGES);
            ps.setInt(1, chat_Owner_id);
            ps.setInt(2, receiverId);
            ps.setInt(3, receiverId);
            ps.setInt(4, chat_Owner_id);
            try (ResultSet resultSet = ps.executeQuery()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
                while (resultSet.next()) {
                    int chat_owner_id = resultSet.getInt("chat_owner_id");
                    String chat_owner_name = resultSet.getString("chat_owner_name");
                    String chat_owner_logo = resultSet.getString("chat_owner_logo");
                    int receiver_id = resultSet.getInt("receiver_id");
                    String receiver_name = resultSet.getString("receiver_name");
                    String receiver_logo = resultSet.getString("receiver_logo");
                    String content = resultSet.getString("content");
                    String dateString = resultSet.getString("date");
                    Date date = null;
                    try {
                        date = sdf.parse(dateString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    User chatOwner = new User(chat_owner_id, chat_owner_name, chat_owner_logo );
                    User receiver = new User(receiver_id, receiver_name, receiver_logo );
                    messages.add(new Message(chatOwner, receiver, content, date));
                }
            }

        }
        return messages;
    }
}
