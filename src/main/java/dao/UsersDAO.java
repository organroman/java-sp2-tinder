package dao;

import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UsersDAO implements Dao<User> {


    private final static String GET_ALL = "SELECT user_id,name,logo FROM users;";
    private final static String GET_BY_ID = "SELECT user_id,name,logo FROM users where user_id=?;";
    private final static String INSERT = "INSERT INTO users (name,logo)VALUES (?,?);";
    private final static String UPDATE = "UPDATE users set name=?,logo=? WHERE user_id=?;";
    private final static String DELETE = "DELETE FROM users WHERE user_id=?;";

    private final static String GET_LOGIN = "SELECT user_id, FROM users where ((name=?)and(password=?));";

    @Override
    public void update(User user) throws SQLException {
        try (Connection connector = Conn.mcConn()) {
            PreparedStatement preparedStatement = connector.prepareStatement(UPDATE);
            preparedStatement.setInt(3, user.getId());
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getImgUrl());
            preparedStatement.execute();
        }

    }

    @Override
    public void insert(User user) throws SQLException {
        try (Connection connector = Conn.mcConn()) {
            PreparedStatement preparedStatement = connector.prepareStatement(INSERT);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getImgUrl());
            preparedStatement.execute();
        }
    }

    @Override
    public void delete(User user) throws SQLException {
        try (Connection connector = Conn.mcConn()) {
            PreparedStatement preparedStatement = connector.prepareStatement(DELETE);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.execute();
        }
    }

    @Override
    public ArrayList<User> select() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        try (Connection connector = Conn.mcConn()) {
            PreparedStatement preparedStatement = connector.prepareStatement(GET_ALL);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("user_id");
                    String name = resultSet.getString("name");
                    String logo = resultSet.getString("logo");
                    users.add(new User(id, name, logo));
                }
            }
        }
        return users;
    }

    public User selectById(int i) throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        try (Connection connector = Conn.mcConn()) {
            PreparedStatement preparedStatement = connector.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, i);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("user_id");
                    String name = resultSet.getString("name");
                    String logo = resultSet.getString("logo");
                    users.add(new User(id, name, logo));
                }
            }
        }
        return users.getFirst() == null ? new User("Bab request", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTR-KgOIasuWW4-30_fqyd0zYyj9al8jK-sug&s") : users.getFirst();
    }

}

