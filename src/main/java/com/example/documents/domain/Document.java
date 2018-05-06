package com.example.documents.domain;

import org.thymeleaf.context.Context;

import java.util.*;

public class Document {

    private String fileName;
    private String template;
    private ArrayList<String> templateParameters;
    private Context context;

    public Document() {
        context = new Context();
        templateParameters = new ArrayList<>();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public ArrayList<String> getTemplateParameters() {
        return templateParameters;
    }

    public void addTemplateParameter(String parameter) {
        this.templateParameters.add(parameter);
    }

    public Context getContext() {
        return context;
    }

    public void setLanguage(String language) {
        this.context.setLocale(new Locale(language));
    }

    public void setData(HashMap<String, Object> data) {
        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            this.context.setVariable(pair.getKey().toString(), pair.getValue());
        }
    }
}
