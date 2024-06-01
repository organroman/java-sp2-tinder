package dao;

import models.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class AuthDAO {
    private  Map<User, String> usersUUID = new HashMap<>();

    public  void update(String userUUID, User obj) throws SQLException {
        usersUUID.put(obj, userUUID);
    }

    public   Map<User, String> select() throws SQLException {
        return usersUUID;
    }

    public  Optional<User> getUserByUUID(String uuid) {
        return usersUUID.entrySet().stream()
                .filter(entry -> entry.getValue().equals(uuid))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public  Optional<Integer> getUserIdByUUID(String uuid) {
        return usersUUID.entrySet().stream()
                .filter(entry -> entry.getValue().equals(uuid))
                .map(entry -> entry.getKey().getId()) // Предполагается, что у User есть метод getId()
                .findFirst();
    }

    public  Optional<String> getUUIDByUser(User user) {
        return usersUUID.entrySet().stream()
                .filter(entry -> entry.getKey().equals(user))
                .map(Map.Entry::getValue)
                .findFirst();
    }
    public Boolean ContainUUID(String uuid) {
        return usersUUID.entrySet().stream()
                .anyMatch(entry -> entry.getValue().equals(uuid));
    }
}
