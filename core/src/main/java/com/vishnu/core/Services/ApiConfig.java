package com.vishnu.core.Services;

import org.osgi.service.metatype.annotations.*;

@ObjectClassDefinition(name = "Country API Config")
public @interface ApiConfig {

    @AttributeDefinition(name = "API Base URL")
    String apiUrl() default "https://restcountries.com/v3.1/name/india";

    @AttributeDefinition(name = "Country Code")
    String countryCode() default "IN";

    
    @AttributeDefinition(name = "API Key") 
    String apiKey() default "";   // ✅ IMPORTANT (no more warning)
}