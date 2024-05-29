package servlets;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class UserServlet extends HttpServlet {
    private final TemplateEngine te;

    public UserServlet(TemplateEngine te) {
        this.te = te;
    }
    int i = 0;

    ArrayList<User> items2 = new ArrayList<>();
    {
        User user1 = new User(1, "GLB", "https://corp.globino.ua/wp-content/uploads/2020/05/logo.png");
        User user2 = new User(2, "MXP", "https://stud-point.com/wp-content/uploads/2024/01/logo_MKHP.png");

        items2.add(user1);
        items2.add(user2);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try (PrintWriter out = resp.getWriter()) {
            te.render("like-page.ftl",  out);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.printf("We recive %s\n" , action);

        resp.sendRedirect("/liked");
    }
}
