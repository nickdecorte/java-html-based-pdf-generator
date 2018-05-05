package com.example.documents.generator;

import com.example.documents.domain.Document;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.AbstractConfigurableTemplateResolver;

import java.util.ArrayList;

@Component
public class TemplateRenderer {

    public String render(Document document) {
        TemplateEngine templateEngine = getTemplateEngine(document.getTemplateParameters());

        return templateEngine.process(document.getTemplate(), document.getContext());
    }

    private TemplateEngine getTemplateEngine(ArrayList<String> parameters) {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(getTemplateResolver(parameters));

        return templateEngine;
    }

    private AbstractConfigurableTemplateResolver getTemplateResolver(ArrayList<String> parameters) {
        DocumentTemplateResolver templateResolver = new DocumentTemplateResolver();
        templateResolver.setParameters(parameters);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        return templateResolver;
    }

}
