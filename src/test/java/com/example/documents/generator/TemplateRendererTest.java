package com.example.documents.generator;

import com.example.documents.domain.Document;
import org.junit.Test;

public class TemplateRendererTest {

    private TemplateRenderer renderer = new TemplateRenderer();

    @Test
    public void render() {
        Document document = new Document();
        document.setFileName("test.pdf");
        document.setTemplate("template");
        document.setLanguage("nl");
        document.setVariable("name", "Nick Decorte");
        document.addTemplateParameter("be");
        document.addTemplateParameter("acme");

        System.out.print(renderer.render(document));
    }
}