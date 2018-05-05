package com.example.documents.generator;

import com.example.documents.domain.Document;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class DocumentGenerator {

    @Autowired
    private TemplateRenderer templateRenderer;

    public ByteArrayOutputStream create(Document document) throws IOException, DocumentException {
        ITextRenderer renderer = new ITextRenderer();

        renderer.setDocumentFromString(templateRenderer.render(document));
        renderer.layout();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        renderer.createPDF(outputStream);

        return outputStream;
    }

}
