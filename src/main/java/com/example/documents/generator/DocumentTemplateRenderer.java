package com.example.documents.generator;

import com.example.documents.domain.Document;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.AbstractConfigurableTemplateResolver;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentTemplateRenderer {

    public String render(Document document) {
        TemplateEngine engine = getTemplateEngine(document.getSpecifiers());

        return engine.process(document.getType(), document.getContext());
    }

    private TemplateEngine getTemplateEngine(List<String> specifiers) {
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(getTemplateResolver(specifiers));

        return engine;
    }

    private AbstractConfigurableTemplateResolver getTemplateResolver(List<String> specifiers) {
        DocumentTemplateResolver resolver = new DocumentTemplateResolver();
        resolver.setParameters(specifiers);
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");

        return resolver;
    }

}
