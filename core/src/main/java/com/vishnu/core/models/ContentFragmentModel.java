package com.vishnu.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

@Model(adaptables = Resource.class)
public class ContentFragmentModel {

    @SlingObject
    private ResourceResolver resolver;

    private List<FragmentData> fragments;

    @PostConstruct
    protected void init() {

        fragments = new ArrayList<>();

        Resource folder = resolver.getResource("/content/dam/aemreactlearning");

        if (folder != null) {

            Iterator<Resource> children = folder.listChildren();

            while (children.hasNext()) {

                Resource child = children.next();

                Resource master = resolver.getResource(child.getPath()
                        + "/jcr:content/data/master");

                if (master != null) {

                    FragmentData data = new FragmentData();

                    data.setName(child.getName());

                    data.setText(master.getValueMap().get("text", String.class));
                    data.setField(master.getValueMap().get("field", String.class));

                    fragments.add(data);
                }
            }
        }
    }

    public List<FragmentData> getFragments() {
        return fragments;
    }
}