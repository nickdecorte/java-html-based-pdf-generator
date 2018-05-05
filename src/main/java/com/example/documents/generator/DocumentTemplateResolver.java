package com.example.documents.generator;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templateresolver.AbstractConfigurableTemplateResolver;
import org.thymeleaf.templateresource.ClassLoaderTemplateResource;
import org.thymeleaf.templateresource.ITemplateResource;

import java.util.*;

public class DocumentTemplateResolver extends AbstractConfigurableTemplateResolver {

    private final ClassLoader classLoader;
    private ArrayList<String> parameters;

    public DocumentTemplateResolver() {
        this((ClassLoader)null);
    }

    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    public DocumentTemplateResolver(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
    @Override
    protected ITemplateResource computeTemplateResource(IEngineConfiguration configuration, String ownerTemplate, String template, String resourceName, String characterEncoding, Map<String, Object> templateResolutionAttributes) {
        List<String> combinations = getTemplateCombinations();

        for (String combination : combinations) {
            String resource = template + combination + ".html";

            if (hasTemplate(resource)) {
                return new ClassLoaderTemplateResource(this.classLoader, resource, characterEncoding);
            }
        }

        return new ClassLoaderTemplateResource(this.classLoader, resourceName, characterEncoding);
    }

    private List<String> getTemplateCombinations() {
        List<String> templates = new ArrayList<>();
        for (String parameter : parameters) {
            String prefix = "";
            if (! templates.isEmpty()) {
                prefix = templates.get(templates.size() - 1);
            }

            templates.add(prefix + "." + parameter);
        }

        Collections.reverse(templates);

        return templates;
    }

    private boolean hasTemplate(String templateResource) {
        return getClass().getResource("/" + templateResource) != null;
    }

}
