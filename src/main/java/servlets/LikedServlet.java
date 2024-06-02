package servlets;

import dao.LikesDAO;
import models.User;
import service.AuthService;
import service.LikeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

public class LikedServlet extends HttpServlet {
    private final TemplateEngine te;
    public LikedServlet(TemplateEngine te) {
        this.te = te;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LikeService likesDAO = new LikeService();
        List<User> likedUsers = new ArrayList<>();

        Optional<Integer> isLoggedInUserId = AuthService.getUserIdByUUID(Auth.getCookieValueUnsafe(req));
        int loggedInUserId = isLoggedInUserId.orElseThrow(() -> new ServletException("User is not logged in"));

        try {
        likedUsers = likesDAO.selectByLike(loggedInUserId);
        } catch (SQLException e) {
            e.printStackTrace();

        }

        Map<String, Object> data = new HashMap<>();
        data.put("users", likedUsers);

        try (PrintWriter w = resp.getWriter()) {
            te.render("liked-list.ftl", data, w);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdParam = req.getParameter("userId");
        if (userIdParam != null) {
            int userId = Integer.parseInt(userIdParam);
            resp.sendRedirect("/messages/" + userId);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing userId parameter");
        }
    }
}
