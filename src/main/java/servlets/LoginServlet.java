package servlets;

import models.User;
import service.AuthService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

public class LoginServlet extends HttpServlet {
    private final TemplateEngine te;
    private final UserService userService = new UserService();
    public LoginServlet(TemplateEngine te ) {
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
        String name = req.getParameter("inputName");
        String password = req.getParameter("inputPassword");

        try {
            Optional<User> userOptional = Auth.getUserByNameAndPassword(name, password);

            if (userOptional.isPresent()) {
                User user = userOptional.get();

                String userUUID = UUID.randomUUID().toString();
                AuthService.update(userUUID, user);

                System.out.println(AuthService.select().toString());
                Auth.setCookieValue(resp, userUUID);
                resp.sendRedirect("/users");
            } else {
                resp.sendRedirect("/login");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Обработка ошибки
        }
    }
}

