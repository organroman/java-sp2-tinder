package servlets;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class ResourceOps {
    public static URL getResource(String prefix) {
        URL resourceUrl = ResourceOps.class.getClassLoader().getResource(prefix);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("Resource not found: " + prefix);
        }
        return resourceUrl;
    }
//TODO: REMOVE
//   public static String dirUnsafe(String prefix) {
//        try {
//            String path =
//                    ResourceOps.class
//                            .getClassLoader()
//                            .getResource(prefix)
//                            .toURI()
//                            .getPath();
//            if (path.startsWith("/C:")) {
//                path = path.substring(3);
//            }
//            return Paths.get(path).toString();  // delete symbols '/' before drive C: if exist
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
