package dao;

import models.Message;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    void insert(T obj) throws SQLException;

    void update(T obj) throws SQLException;

    void delete(T obj) throws SQLException;

    List<T> select() throws SQLException;


}
