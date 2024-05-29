import filters.CookieFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServlet;
import java.util.EnumSet;

public class Application {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        TemplateEngine te = new TemplateEngine("templates");
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new HelloServlet()), "/");
        handler.addServlet(new ServletHolder(new UserServlet(te)), "/users");
        handler.addServlet(new ServletHolder(new ChatServlet(te)), "/messages/*");
        handler.addServlet(new ServletHolder(new LikedServlet(te)), "/liked");
        handler.addServlet(new ServletHolder(new LoginServlet(te)), "/login");
        handler.addServlet(new ServletHolder(new StaticContentServlet("css")), "/css/*");

        EnumSet<DispatcherType> dt = EnumSet.of(DispatcherType.REQUEST);

        handler.addFilter(CookieFilter.class, "/users", dt);
        handler.addFilter(CookieFilter.class, "/messages/*", dt);
        handler.addFilter(CookieFilter.class, "/liked", dt);

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
