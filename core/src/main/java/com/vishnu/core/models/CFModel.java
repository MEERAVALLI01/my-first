package com.vishnu.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
    adaptables = Resource.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CFModel {

    @Self
    private Resource resource;

    @ValueMapValue
    private String fragmentPath;

    private String text;
    private String image;
    private String number;

    @PostConstruct
    
    protected void init() {

        try {
            if (fragmentPath != null && !fragmentPath.isEmpty()) {

                ResourceResolver resolver = resource.getResourceResolver();

                Resource fragmentResource = resolver.getResource(
                        fragmentPath + "/jcr:content/data/master"
                );

                if (fragmentResource != null) {

                    text = fragmentResource.getValueMap().get("text", String.class);
                    image = fragmentResource.getValueMap().get("image", String.class);
                    number = fragmentResource.getValueMap().get("number", String.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }

    public String getNumber() {
        return number;
    }
}