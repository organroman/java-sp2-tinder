package service;

import dao.UsersDAO;
import models.User;

import java.sql.SQLException;
import java.util.ArrayList;


public class UserService {

    UsersDAO dao = new UsersDAO();

    public void insert(User user) throws SQLException {
        dao.insert(user);
    }

    public void update(User user) throws SQLException {
        dao.update(user);
    }


    public void delete(User user) throws SQLException {
        dao.delete(user);
    }


    public ArrayList<User> selectAll() throws SQLException {
        return dao.select();
    }

    public User selectById(int id) throws SQLException {
        return dao.selectById(id);
    }
}
