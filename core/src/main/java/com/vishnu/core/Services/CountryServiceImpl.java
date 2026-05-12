package com.vishnu.core.Services;

import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;

import java.util.Arrays;
import java.util.List;

@Component(service = CountryService.class)
@Designate(ocd = CountryConfig.class)
public class CountryServiceImpl implements CountryService {

    private List<String> countries;

    // @Activate
    // protected void activate(CountryConfig config) {
    //     countries = Arrays.asList(config.countries());
    // }

    @Activate
protected void activate(CountryConfig config) {
    countries = Arrays.asList(config.countries());
    System.out.println("OSGi Countries: " + countries);
}

    public List<String> getCountries() {
        return countries;
    }
}