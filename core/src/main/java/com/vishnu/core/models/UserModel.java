package com.vishnu.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.*;

import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.vishnu.core.Services.MyService;


@Model(
    adaptables = SlingHttpServletRequest.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class UserModel {

    @OSGiService
    private MyService myService;

    @ValueMapValue
    private String userName;

    @ValueMapValue
    private String email;

    public String getUserName() {

        if (userName != null && !userName.isEmpty()) {
            return userName;
        }

       
        return myService != null ? myService.getUser() : "No Data";
    }

    public String getEmail() {

        if (email != null && !email.isEmpty()) {
            return email;
        }

        return myService != null ? myService.getEmail() : "No Data";
    }
}