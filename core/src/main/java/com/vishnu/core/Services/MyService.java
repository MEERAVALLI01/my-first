package com.vishnu.core.Services;

import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = MyService.class, immediate = true)
@Designate(ocd = UserConfig.class)
public class MyService {

    private String userName;
    private String email;

    @Activate
    @Modified
    protected void activate(UserConfig config) {
        this.userName = config.userName();
        this.email = config.email();

        System.out.println("OSGi Updated → " + userName + " | " + email);
    }

    public String getUser() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
 