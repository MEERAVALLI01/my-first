package com.vishnu.core.servlets;



import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.*;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.osgi.service.component.annotations.*;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.vishnu.core.Services.CountryService;

import javax.servlet.Servlet;
import java.util.*;

@Component(
    service = Servlet.class,
    property = {
        "sling.servlet.resourceTypes=aemreactlearning/datasource/countries",
        "sling.servlet.methods=GET"
    }
)
public class CountryDataSourceServlet extends SlingSafeMethodsServlet {

    @Reference
    private CountryService countryService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {

        List<Resource> options = new ArrayList<>();

        for (String country : countryService.getCountries()) {

            ValueMap vm = new ValueMapDecorator(new HashMap<>());
            vm.put("text", country);
            vm.put("value", country);

            options.add(new ValueMapResource(
                    request.getResourceResolver(),
                    new ResourceMetadata(),
                    "nt:unstructured",
                    vm
            ));
        }

        DataSource ds = new SimpleDataSource(options.iterator());
        request.setAttribute(DataSource.class.getName(), ds);
    }
}