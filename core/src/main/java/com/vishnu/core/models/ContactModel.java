package com.vishnu.core.models;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

@Model(adaptables = Resource.class)
public class ContactModel {

    @SlingObject
    ResourceResolver resolver;

    private List<Resource> list = new ArrayList<>();

    @PostConstruct
    protected void init() {
        Resource parent = resolver.getResource("/content/crx/de/contactdata");

        if (parent != null) {
            for (Resource child : parent.getChildren()) {
                list.add(child);
            }
        }
    } 
    // test

    public List<Resource> getList() {
        return list;
    }
}
