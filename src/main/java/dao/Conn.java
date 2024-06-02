package dao;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
    @SneakyThrows
    public static Connection mcConn() {


        return DriverManager.getConnection(
                "jdbc:postgresql://ep-calm-math-a54c9vuy.us-east-2.aws.neon.tech/fs8_step2_team1",
                "fs8_step2_team1_owner",
                "Yi95WmnqTBed" );  //remote  DB connection

    }
}
