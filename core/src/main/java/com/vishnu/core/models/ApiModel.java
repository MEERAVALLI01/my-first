package com.vishnu.core.models;

import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.vishnu.core.Services.ApiService;

import javax.annotation.PostConstruct;

@Model(adaptables = org.apache.sling.api.SlingHttpServletRequest.class)
public class ApiModel {

    @OSGiService
    private ApiService apiService;

    private String apiData;

    @PostConstruct
    protected void init() {
        if (apiService != null) {
            apiData = apiService.getApiResponse();
        } else {
            apiData = "Service not available";
        }
    }

    public String getApiData() {
        return apiData;
    }
}