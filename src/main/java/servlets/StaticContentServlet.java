package servlets;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


public class StaticContentServlet extends HttpServlet {

    private final String prefix;

    public StaticContentServlet(String root) {
        this.prefix = root;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fileName = req.getPathInfo();
        String fullName = prefix + fileName;

        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(fullName);
        if (resourceStream == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Set MIME type
        if (fileName.endsWith(".css")) {
            resp.setContentType("text/css");
        }

        try (ServletOutputStream os = resp.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = resourceStream.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } finally {
            if (resourceStream != null) {
                resourceStream.close();
            }
        }
    }
}
