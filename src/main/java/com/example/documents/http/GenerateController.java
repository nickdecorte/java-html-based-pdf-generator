package com.example.documents.http;

import com.example.documents.domain.Document;
import com.example.documents.generator.DocumentGenerator;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class GenerateController {

    @Autowired
    private DocumentGenerator documentGenerator;

    @RequestMapping("/")
    public ResponseEntity<byte[]> generate(Model model) throws IOException, DocumentException {
        Document document = new Document();
        document.setFileName("test.pdf");
        document.setTemplate("template");
        document.setLanguage("nl");
        document.setVariable("name", "Nick Decorte");
        document.addTemplateParameter("be");
        document.addTemplateParameter("acme");

        ByteArrayOutputStream outputStream = documentGenerator.create(document);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData(document.getFileName(), document.getFileName());
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<byte[]>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

}