package servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


public class TemplateEngine {
    private final Configuration cfg;

    public TemplateEngine(String rootFolder) {
        cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setClassForTemplateLoading(TemplateEngine.class, "/" + rootFolder);
    }

    public void render(String templateName, Map<String, Object> data, Writer w) {
        try {
            Template template = cfg.getTemplate(templateName);
            template.process(data, w);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    public void render(String templateName, Writer w) {
        render(templateName, new HashMap<>(), w);
    }
}