package com.vishnu.core.models;

import org.apache.sling.api.resource.Resource;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class ElementItem {

    @ValueMapValue
    private String linkTitle;

    @ValueMapValue
    private String link;

    @ValueMapValue
    private boolean checkbox;

    public String getLinkTitle() {
        return linkTitle;
    }

    public String getLink() {
        return link;
    }

   public boolean isCheckbox() {
    return checkbox;
}
}
