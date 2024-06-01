package filters;
import service.AuthService;
import servlets.Auth;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieFilter implements HttpFilter {

    @Override
    public boolean checkLogic(HttpServletRequest rq0) {
        return (Auth.getCookieValue(rq0).isPresent() && AuthService.containUUID(Auth.getCookieValue(rq0).get())) ;

    }

    @Override
    public void ifNotPassed(HttpServletRequest rq, HttpServletResponse rs) throws IOException {
        rs.sendRedirect("/login");
    }

}
