package com.example.documents.http;

import com.example.documents.domain.Document;
import com.example.documents.generator.DocumentGenerator;
import com.example.documents.generator.DocumentTemplateRenderer;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

@RestController
public class GenerateController {

    @Autowired
    private DocumentTemplateRenderer documentTemplateRenderer;

    @Autowired
    private DocumentGenerator documentGenerator;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<byte[]> generate(@RequestBody Document document) throws IOException, DocumentException {
        ByteArrayOutputStream outputStream = documentGenerator.create(document);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData(document.getFile(), document.getFile());
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<byte[]>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String generateTest() throws IOException, DocumentException {
        Document document = new Document();
        document.setType("declarationoforigin");
        document.setSpecifiers(Arrays.asList("be"));

        return documentTemplateRenderer.render(document);
    }

}