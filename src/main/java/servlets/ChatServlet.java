package servlets;

import controller.MessageController;
import service.UserService;
import models.Message;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatServlet extends HttpServlet {
    private final TemplateEngine te;

    public ChatServlet(TemplateEngine te) {
        this.te = te;
    }

    static MessageController messageController = new MessageController();
    static UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//todo: get loggedIn user and replace it with hardcoded 1
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing user ID");
        }

        String[] pathParts = pathInfo.split("/");
        if (pathParts.length < 2) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID");
        }

        String userIdStr = pathParts[1];
        int userId;
        try {
            userId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format");
            return;
        }

        List<Message> messages;

        try {
            messages = messageController.getMessages(1, userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> data = new HashMap<>();
        User chatPartner;

        try {
            chatPartner = userService.selectById(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        data.put("chatPartner", chatPartner);
        data.put("messages", messages);

        try (PrintWriter out = resp.getWriter()) {
            te.render("chat.ftl", data, out);
        }
    }
//todo: implement doPost
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
