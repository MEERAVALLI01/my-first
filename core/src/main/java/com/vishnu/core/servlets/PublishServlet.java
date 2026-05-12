package com.vishnu.core.servlets;




import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.vishnu.core.Services.ReplicationService;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(
    service = Servlet.class,
    property = {
        "sling.servlet.paths=/bin/publishPage",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET
    }
)
public class PublishServlet extends SlingAllMethodsServlet {

    @Reference
    private ReplicationService replicationService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws IOException {

        String path = request.getParameter("path");

                replicationService.publishPage(request.getResourceResolver());
        response.getWriter().write("Published: " + path);
    }
}