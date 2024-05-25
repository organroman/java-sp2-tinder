package servlets;

import java.net.URISyntaxException;

public class ResourceOps {

    public static String dirUnsafe(String prefix) {
        try {
            String s = ResourceOps.class
                    .getClassLoader()
                    .getResource(prefix)
                    .toURI()
                    .getPath();    //    "/C:/*"
            return s.substring(s.lastIndexOf(':') - 1); // delete symbols '/' before drive C: if exist
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
