package com.vishnu.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ExporterConstants;

@Model(
    adaptables = Resource.class,
    resourceType = "aemreactlearning/components/container",
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

@Exporter(
    name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
    extensions = ExporterConstants.SLING_MODEL_EXTENSION
)

public class Oopo {

    @ValueMapValue
    private String laptop;

    public String getLaptop(){
        return laptop;
    }
}