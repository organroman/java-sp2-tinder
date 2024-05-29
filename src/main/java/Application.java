import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

import javax.servlet.http.HttpServlet;

public class Application {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        TemplateEngine te = new TemplateEngine("templates");
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new HelloServlet()), "/");

        server.setHandler(handler);
        handler.addServlet(new ServletHolder(new UserServlet(te)), "/users");
        handler.addServlet(new ServletHolder(new ChatServlet(te)), "/messages/*");

        handler.addServlet(new ServletHolder(new StaticContentServlet("css")), "/css/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
