package servlets;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StaticContentServlet extends HttpServlet {

    private final String prefix;

    public StaticContentServlet(String root) {
//        TODO: REMOVE
//        this.prefix = ResourceOps.dirUnsafe(root);
        this.prefix = root;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1
        String fileName = req.getPathInfo();
        String fullName = prefix + fileName;
//TODO: REMOVE
//        System.out.println(fileName);
//        System.out.println(fullName);

//        if (!new File(fullName).exists()) {
//            // 3
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        } else try (ServletOutputStream os = resp.getOutputStream()) {
//            // 2
//            Path path = Paths.get(fullName);
//            // 3
//            Files.copy(path, os);
//        }
        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(fullName);
        if (resourceStream == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            try (ServletOutputStream os = resp.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = resourceStream.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            } finally {
                resourceStream.close();
            }
        }
    }
}
