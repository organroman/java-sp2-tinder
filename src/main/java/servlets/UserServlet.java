package servlets;

import dao.UserService;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
        try {
            items2 = new UserService().selectAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> map = getItems2();
        try (PrintWriter out = resp.getWriter()) {
            te.render("like-page.html", map, out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null != req.getParameter("x")) {
            try (PrintWriter out = resp.getWriter()) {
                System.out.println(req.getParameter("like"));
                if (null != req.getParameter("like")) {
                    if (req.getParameter("like").equals("like")) {

                        // do save liked profiles

                    }
                }else resp.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
                if (i != items2.size() - 1) i++;
                else resp.sendRedirect("/liked");
                HashMap<String, Object> map = getItems2();
                te.render("like-page.html", map, out);

            }
        } else resp.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
    }

    public HashMap<String, Object> getItems2() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("items2", items2.get(i));
        map.put("name", items2.get(i).getName());
        map.put("logo", items2.get(i).getImgUrl());
        map.put("id", items2.get(i).getId());
        return map;
    }

}