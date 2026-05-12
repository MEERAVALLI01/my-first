package com.vishnu.core.Services;


import org.osgi.service.metatype.annotations.*;

@ObjectClassDefinition(name = "Country Config")
public @interface CountryConfig {

    @AttributeDefinition(name = "Countries")
    String[] countries() default {};
}
