package dao;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
    @SneakyThrows
    static Connection mcConn() {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/user_step_base",
                "postgres",
                "postgres"     // to do for remote db
        );
    }
}
