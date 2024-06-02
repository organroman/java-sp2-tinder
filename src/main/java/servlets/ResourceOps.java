package servlets;


import java.net.URL;

public class ResourceOps {
    public static URL getResource(String prefix) {
        URL resourceUrl = ResourceOps.class.getClassLoader().getResource(prefix);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("Resource not found: " + prefix);
        }
        return resourceUrl;
    }
}
