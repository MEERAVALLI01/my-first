package com.vishnu.core.Services;



import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;


@ObjectClassDefinition(
    name = "User Configuration",
    description = "Default values for User Component"
)
public @interface UserConfig {

    @AttributeDefinition(
        name = "Default User Name",
        description = "Default name if not provided in component"
    )
    String userName() default "Default User";

    @AttributeDefinition(
        name = "Default Email",
        description = "Default email if not provided in component"
    )
    String email() default "default@mail.com";
}