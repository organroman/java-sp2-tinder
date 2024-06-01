package dao;

import models.Like;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LikesDAO implements Dao<Like> {

    private final static String INSERT = "INSERT INTO likes (who,whom,action)VALUES (?,?,?);";
    private final static String GET_ALL = "SELECT id,who,whom,action FROM likes;";
    private final static String GET_BY_USER_ID_lIKED = "SELECT whom FROM likes where ((who=?)and(action=?));";
    private final static String GET_ID = "SELECT id FROM likes WHERE ((who=?)and(whom=?));";
    private final static String UPDATE = "UPDATE likes set who=?,whom=?,action=? WHERE id=?;";
    private final static String DELETE = "DELETE FROM likes WHERE id=?;";


    @Override
    public void update(Like like) throws SQLException {
        if (like.getId() != 0)
            try (Connection connector = Conn.mcConn()) {
                PreparedStatement preparedStatement = connector.prepareStatement(UPDATE);
                preparedStatement.setInt(4, like.getId());
                preparedStatement.setInt(1, like.getId_who());
                preparedStatement.setInt(2, like.getId_whom());
                preparedStatement.setString(3, like.getAction());
                preparedStatement.execute();
            }
        else insert(like);
    }

    @Override
    public void insert(Like like) throws SQLException {
        try (Connection connector = Conn.mcConn()) {
            PreparedStatement preparedStatement = connector.prepareStatement(INSERT);
            preparedStatement.setInt(1, like.getId_who());
            preparedStatement.setInt(2, like.getId_whom());
            preparedStatement.setString(3, like.getAction());
            preparedStatement.execute();
        }
    }

    @Override
    public void delete(Like like) throws SQLException {
        try (Connection connector = Conn.mcConn()) {
            PreparedStatement preparedStatement = connector.prepareStatement(DELETE);
            preparedStatement.setInt(1, like.getId());
            preparedStatement.execute();
        }
    }

    @Override
    public ArrayList<Like> select() throws SQLException {
        ArrayList<Like> likes = new ArrayList<>();
        try (Connection connector = Conn.mcConn()) {
            PreparedStatement preparedStatement = connector.prepareStatement(GET_ALL);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int who = resultSet.getInt("who");
                    int whom = resultSet.getInt("whom");
                    String action = resultSet.getString("action");
                    likes.add(new Like(id, who, whom, action));
                }
            }
        }
        return likes;
    }

    public ArrayList<User> selectByLike(int i) throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        try (Connection connector = Conn.mcConn()) {
            PreparedStatement preparedStatement = connector.prepareStatement(GET_BY_USER_ID_lIKED);
            preparedStatement.setInt(1, i);
            preparedStatement.setString(2, "like");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new UserService().selectById(resultSet.getInt("whom"));
                    users.add(user);
                }
            }
        }
        return users;
    }

    public int selectIdByUsers(int who, int whom) throws SQLException {
        int id = 0;
        try (Connection connector = Conn.mcConn()) {
            PreparedStatement preparedStatement = connector.prepareStatement(GET_ID);
            preparedStatement.setInt(1, who);
            preparedStatement.setInt(2, whom);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                }
            }
        }
        return id;
    }

}