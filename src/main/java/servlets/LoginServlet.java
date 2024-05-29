package servlets;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class LoginServlet extends HttpServlet {
    private final TemplateEngine te;

    public LoginServlet(TemplateEngine te) {
        this.te = te;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("x");
        String password = req.getParameter("y");


        try (PrintWriter w = resp.getWriter()) {
            te.render("login.ftl", w);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Dao
        List<Map<User, UUID>> users = new ArrayList<>();
        //Test Data


        User user = new User("Test@gmail.com", "test", 1);
        UUID userUUID = UUID.randomUUID();
        Map<User, UUID> userMap = new HashMap<>();
        userMap.put(user , userUUID);

        // Добавляем HashMap в коллекцию users
        users.add(userMap);
        String login = req.getParameter("inputEmail");
        String password = req.getParameter("inputPassword");

        // Метод поиска по логину и паролю, возвращает Optional<User>
        Optional<User> foundUser = users.stream()
                .flatMap(map -> map.keySet().stream())
                .filter(u -> u.getLogin().equals(login) && u.getPassword().equals(password))
                .findFirst();

        // Проверка результата
        if (foundUser.isPresent()) {
            Auth.setCookieValue(
                    resp,
                    UUID.randomUUID().toString());
        }
        else {
            User user1 = new User(login , password , 2);
            UUID userUUID1 = UUID.randomUUID();
            Map<User, UUID> userMapTest = new HashMap<>();
            userMapTest.put(user , userUUID);
            users.add(userMapTest);
            Auth.setCookieValue(
                    resp,
                    UUID.randomUUID().toString());
        }


        resp.sendRedirect("/users");
    }
}
