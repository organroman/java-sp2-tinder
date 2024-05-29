package servlets;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LikedServlet extends HttpServlet {
    private final TemplateEngine te;
    public LikedServlet(TemplateEngine te) {
        this.te = te;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> likedUsers = new ArrayList<>();
        likedUsers.add(new User(1,"John Doe", "https://robohash.org/johndoe.png"));
        likedUsers.add(new User(2,"Jane Smith", "https://robohash.org/janesmith.png"));
        likedUsers.add(new User(3,"J4ane Smith", "https://robohash.org/janesmith.png"));
        likedUsers.add(new User(4,"Ja1ne Smith", "https://robohash.org/janesmith.png"));
        likedUsers.add(new User(5,"Jan2e Smith", "https://robohash.org/janesmith.png"));
        likedUsers.add(new User(6,"Ja3ne Smith", "https://robohash.org/janesmith.png"));
        // Добавьте больше пользователей по желанию

        Map<String, Object> data = new HashMap<>();
        data.put("users", likedUsers);

        try (PrintWriter w = resp.getWriter()) {
            te.render("liked-list.ftl", data, w);
        }
    }
}
