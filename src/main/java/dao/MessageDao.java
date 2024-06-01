package dao;


import models.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageDao extends Dao<Message> {
    List<Message> select(int chatOwner_id, int receiverId) throws SQLException;
}
