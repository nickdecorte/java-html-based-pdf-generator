package com.example.documents.generator;

import com.example.documents.domain.Document;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

public class TemplateRendererTest {

    private TemplateRenderer renderer = new TemplateRenderer();

    @Test
    public void render() throws IOException {

        String test = "{ \"data\":{ \"company\": \"acme\", \"date\": \"04/05/2018\", \"products\": [{ \"name\": \"Coca Cola\", \"price\": \"1.80\" },{ \"name\": \"Sprite\", \"price\": \"1.90\" }] } }";

        HashMap<String, Object> result = new ObjectMapper().readValue(test, HashMap.class);

        Document document = new Document();
        document.setFileName("test.pdf");
        document.setTemplate("invoice/master");
        document.setLanguage("nl");
        document.setData(result);
        document.addTemplateParameter("be");
        document.addTemplateParameter("acme");

        System.out.print(renderer.render(document));
    }
}