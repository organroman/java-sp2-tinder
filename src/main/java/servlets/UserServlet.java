package servlets;


import dao.UserService;
import models.Like;
import models.User;
import service.LikeService;

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
                if (null != req.getParameter("like")) {
                    try {
                        collect(req);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else resp.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
                if (i != items2.size() - 1) i++;
                else {
                    i = 0;          // reset loop
                    try {
                        System.out.println(new LikeService().selectByLike(1));     // add parse user id
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    resp.sendRedirect("/liked");
                }
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

    public void collect(HttpServletRequest req) throws SQLException {
        LikeService likeService = new LikeService();
        int who_id = 1;
        int whom_id = Integer.parseInt(req.getParameter("x"));
        if (whom_id != who_id) {              // impossible likes yourself
            int flag = likeService.selectIdByUsers(who_id, whom_id);
            if (req.getParameter("like").equals("like")) {
                likeService.update(new Like(flag, who_id, whom_id, "like"));
            } else likeService.update(new Like(flag, who_id, whom_id, "dislike"));
        }
    }
}