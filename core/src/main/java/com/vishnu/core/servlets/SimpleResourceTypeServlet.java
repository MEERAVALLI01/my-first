package com.vishnu.core.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import org.osgi.service.component.annotations.Component;
import org.apache.sling.api.servlets.ServletResolverConstants;

@Component(
    service = Servlet.class,
    property = {
        ServletResolverConstants.SLING_SERVLET_RESOURCE_TYPES + "=vishnu/components/page",
        ServletResolverConstants.SLING_SERVLET_METHODS + "=GET",
        ServletResolverConstants.SLING_SERVLET_EXTENSIONS + "=json"
    }
)
public class SimpleResourceTypeServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.getWriter().write("ResourceType Servlet is Working");
    }
}
