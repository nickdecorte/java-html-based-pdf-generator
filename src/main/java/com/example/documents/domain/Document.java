package com.example.documents.domain;

import org.thymeleaf.context.Context;

import java.util.*;

public class Document {

    private String file;
    private String type;
    private List<String> specifiers;
    private Context context;

    public Document() {
        context = new Context();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type + "/master";
    }

    public List<String> getSpecifiers() {
        return specifiers;
    }

    public void setSpecifiers(List<String> specifiers) {
        this.specifiers = specifiers;
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
