package service;

import dao.AuthDAO;
import models.User;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class AuthService {
    private static final AuthDAO authDAO = new AuthDAO();

    public static void update(String userUUID, User obj) throws SQLException {
        authDAO.update(userUUID,obj);
    }

    public static Map<User, String> select() throws SQLException {
        return authDAO.select();
    }

    public static Optional<User> getUserByUUID(String uuid) {
        return authDAO.getUserByUUID(uuid);
    }

    public static   Optional<Integer> getUserIdByUUID(String uuid) {
        return authDAO.getUserIdByUUID(uuid);
    }

    public static   Optional<String> getUUIDByUser(User user) {
      return authDAO.getUUIDByUser(user);
    }
    public static Boolean containUUID(String uuid) {
        return authDAO.ContainUUID(uuid);
    }
}
