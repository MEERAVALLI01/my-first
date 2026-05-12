// package com.vishnu.core.models;

// import org.apache.sling.api.resource.Resource;
// import org.apache.sling.models.annotations.*;
// import org.apache.sling.models.annotations.injectorspecific.OSGiService;
// import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

// import com.vishnu.core.Services.CountryService;

// import java.util.List;

// @Model(
//     adaptables = Resource.class,
//     defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
// )
// public class CountryModel {

//     @OSGiService
//     private CountryService countryService;

//     @ValueMapValue
//     private String country; // from dialog (comma separated)

//     public List<String> getCountry() {

//     // if (countries != null && !countries.isEmpty()) {
//     //     return Arrays.asList(countries.split("\\n+")); // split by space
//     // }
//     return countryService.getCountries();
// }
// }

package com.vishnu.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
    adaptables = Resource.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CountryModel {

    @ValueMapValue
    private String country;

    public String getCountry() {
        return country;
    }
}