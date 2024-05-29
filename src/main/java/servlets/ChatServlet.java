package servlets;

import models.Message;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatServlet extends HttpServlet {
    private final TemplateEngine te;

    public ChatServlet(TemplateEngine te) {
        this.te = te;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing user ID");
        }

        String[] pathParts = pathInfo.split("/");
        if(pathParts.length < 2) {
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



        Map<String, Object> data = new HashMap<>();

         User chatOwner = new User( 5,"David", "https://cdn6.f-cdn.com/contestentries/1211056/26635841/5a3a4944e8ce3_thumb900.jpg");
         User chatPartner = new User( userId,"Sarah", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdkzGP4c2gM7cy1zw6va3pdO0zLThQpVOkEVNTyn5xcJiQCOMP_6JmxREp6ZL8qY4VXsk&usqp=CAU");

         data.put("chatPartner", chatPartner);
         ArrayList<Message> messages = new ArrayList<>();
//        messages.add(new Message(David, Sarah, "Hi There", "2024-05-20 22:15"));
//        messages.add(new Message(Sarah, David, "Hello", "2024-05-20 22:17"));
        for (int i = 0; i < 40; i++) {
            if (i % 2 == 0) {
                messages.add(new Message(chatPartner, chatOwner, "Message " + i + " from David", "2024-05-20 22:1" + i));
                messages.add(new Message(chatPartner, chatOwner, "How are you", "2024-05-20 22:11"));
            } else {
                messages.add(new Message(chatOwner, chatPartner, "Message " + i + " from Sarah", "2024-05-20 22:1" + i));
            }
        }
        data.put("messages", messages);

        try (PrintWriter out = resp.getWriter()) {
            te.render("chat.ftl", data, out);

        }


    }
}
