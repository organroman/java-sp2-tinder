package org.example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class Auth {

    private final static String CookieName = "CID";
    private final static Cookie[] EMPTY = new Cookie[0];
    public final static RuntimeException EX = new IllegalStateException("no cookie!");

    private static Cookie[] ensureNotNull(Cookie[] cookies) {
        return cookies != null ? cookies : EMPTY;
    }

    public static Optional<Cookie> getCookie(HttpServletRequest rq) {
        Cookie[] cookies = ensureNotNull(rq.getCookies()); // null ;(

        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(CookieName))
                .findFirst();
    }

    // 1
    public static Optional<String> getCookieValue(HttpServletRequest rq) {
        return getCookie(rq).map(Cookie::getValue);
    }

    public static String getCookieValueUnsafe(HttpServletRequest rq) {
        return getCookieValue(rq).orElseThrow(() -> EX);
    }

    public static void setCookie(HttpServletResponse rs, Cookie cookie) {
        cookie.setPath("/");
        rs.addCookie(cookie);
    }

    // 2
    public static void setCookieValue(HttpServletResponse rs, String cookieValue) {
        Cookie cookie = new Cookie(CookieName, cookieValue);
        cookie.setMaxAge(10 * 60); // TTL in seconds (10 min)
        setCookie(rs, cookie);
    }

    // 3
    public static void removeCookie(HttpServletResponse rs) {
        Cookie cookie = new Cookie(CookieName, "");
        cookie.setMaxAge(0);
        rs.addCookie(cookie);
    }

}
