// package com.vishnu.core.Services;

// import org.osgi.service.component.annotations.*;
// import org.osgi.service.metatype.annotations.*;

// @Component(service = MyService.class, immediate = true)
// @Designate(ocd = MyServiceImpl.Config.class)
// public class MyServiceImpl implements Myimp  {

//     @ObjectClassDefinition(name = "My OSGI Service")
//     public @interface Config {

//         @AttributeDefinition(
//                 name = "Names",
//                 description = "Add values one by one"
//         )
//         String[] names();
//     }

//     private String[] names;

//     @Activate
//     @Modified
//     protected void activate(Config config) {
//         this.names = config.names();
//     }

//     public String[] getNames() {
//         return names;
//     }
// }