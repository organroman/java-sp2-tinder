package servlets;

import dao.Dao;
import dao.UserService;
import dao.UsersDAO;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import dao.Conn;

public class Auth {

    private final static String CookieName = "CID";
    private final static Cookie[] EMPTY = new Cookie[0];
    public final static RuntimeException EX = new IllegalStateException("no cookie!");
    private final static String GET_BY_NAME_AND_PASSWORD = "SELECT user_id, name, logo FROM users WHERE name=? AND password=?;";

    private static Cookie[] ensureNotNull(Cookie[] cookies) {
        return cookies != null ? cookies : EMPTY;
    }
    private UserService userService;

    public Auth (UserService userService){
        this.userService = userService;
    }

    public static Optional<Cookie> getCookie(HttpServletRequest rq) {
        Cookie[] cookies = ensureNotNull(rq.getCookies()); // null ;(

        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(CookieName))
                .findFirst();
    }

    // 1
    public static Optional<String> getCookieValue(HttpServletRequest rq) {
        return getCookie(rq).map(Cookie::getValue);
    }

    public static String getCookieValueUnsafe(HttpServletRequest rq) {
        return getCookieValue(rq).orElseThrow(() -> EX);
    }

    public static void setCookie(HttpServletResponse rs, Cookie cookie) {
        cookie.setPath("/");
        rs.addCookie(cookie);
    }

    // 2
    public static void setCookieValue(HttpServletResponse rs, String cookieValue) {
        Cookie cookie = new Cookie(CookieName, cookieValue);
        cookie.setMaxAge(10 * 60); // TTL in seconds (10 min)
        setCookie(rs, cookie);
    }

    // 3
    public static void removeCookie(HttpServletResponse rs) {
        Cookie cookie = new Cookie(CookieName, "");
        cookie.setMaxAge(0);
        rs.addCookie(cookie);
    }
    public static Optional<User> getUserByNameAndPassword(String name, String password) throws SQLException {
        try (Connection connector = Conn.mcConn()) {
            PreparedStatement preparedStatement = connector.prepareStatement(GET_BY_NAME_AND_PASSWORD);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String userName = resultSet.getString("name");
                String userLogo = resultSet.getString("logo");

                User user = new User(userId, userName, userLogo);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        }
    }
}
