package dao;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
    @SneakyThrows
    public static Connection mcConn() {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5435/fs8_step_team1",
                "fs8_team1",
                "fs8_team123"
//                "postgres"     // todo for remote db
        );
    }
}
