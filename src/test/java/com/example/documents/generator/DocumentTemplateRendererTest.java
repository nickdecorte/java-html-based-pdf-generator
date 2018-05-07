package com.example.documents.generator;

import com.example.documents.domain.Document;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class DocumentTemplateRendererTest {

    private DocumentTemplateRenderer renderer = new DocumentTemplateRenderer();

    @Test
    public void render() throws IOException {

        String test = "{ \"data\":{ \"company\": \"acme\", \"date\": \"04/05/2018\", \"products\": [{ \"name\": \"Coca Cola\", \"price\": \"1.80\" },{ \"name\": \"Sprite\", \"price\": \"1.90\" }] } }";

        HashMap<String, Object> result = new ObjectMapper().readValue(test, HashMap.class);

        Document document = new Document();
        document.setFile("test.pdf");
        document.setType("invoice");
        document.setLanguage("nl");
        document.setData(result);
        document.setSpecifiers(Arrays.asList("be", "acme"));

        System.out.print(renderer.render(document));
    }
}