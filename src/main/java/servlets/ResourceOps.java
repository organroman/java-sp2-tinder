package servlets;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public class ResourceOps {

    public static String dirUnsafe(String prefix) {
        try {
            String path =
                    ResourceOps.class
                            .getClassLoader()
                            .getResource(prefix)
                            .toURI()
                            .getPath();
            if (path.startsWith("/C:")) {
                path = path.substring(3);
            }
            return Paths.get(path).toString();  // delete symbols '/' before drive C: if exist
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
