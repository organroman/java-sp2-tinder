package servlets;

import controller.MessageController;
import service.UserService;
import models.Message;
import models.User;
import service.AuthService;

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
import java.util.Optional;

public class ChatServlet extends HttpServlet {
    private final TemplateEngine te;

    public ChatServlet(TemplateEngine te) {
        this.te = te;
    }

    static MessageController messageController = new MessageController();
    static UserService userService = new UserService();


    private int getUserIdFromPath (HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing user ID");
            return -1;
        }

        String[] pathParts = pathInfo.split("/");
        if (pathParts.length < 2) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID");
            return -1;
        }

        String userIdStr = pathParts[1];
        int userId;
        try {
            userId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format");
            return -1;
        }

        return userId;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Integer> isLoggedInUserId = AuthService.getUserIdByUUID(Auth.getCookieValueUnsafe(req));
        int loggedInUserId = isLoggedInUserId.orElseThrow(() -> new ServletException("User is not logged in"));

        int userId = getUserIdFromPath(req, resp);
        if (userId == -1) return;

        List<Message> messages;

        try {
            messages = messageController.getMessages(loggedInUserId, userId);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = req.getParameter("content");

        if (content == null || content.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Message is required");
            return;
        }

        int userId = getUserIdFromPath(req, resp);
        if (userId == -1) return;

        Optional<User> isChatOwner = AuthService.getUserByUUID(Auth.getCookieValueUnsafe(req));
        User loggedInUser = isChatOwner.orElseThrow(() -> new ServletException("User is not logged in"));


        User receiver;
        try {
            receiver = userService.selectById(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Message message = new Message(loggedInUser, receiver, content, new java.util.Date());

        try {
            messageController.addMessage(message);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect(req.getContextPath() + req.getServletPath() + "/" + userId);
    }

}
