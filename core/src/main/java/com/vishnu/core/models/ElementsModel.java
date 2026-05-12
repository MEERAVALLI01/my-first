package com.vishnu.core.models;


import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.Via;
@Model(
    adaptables = Resource.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ElementsModel {
    
    @ChildResource(name = "elements")
    @Via("resource")
    private List<ElementItem> elements;

    @ValueMapValue
    private String ariaLabel;

    public List<ElementItem> getElements(){
        return elements;
    }

    public String getAriaLabel() {
        return ariaLabel;
    }
}
   