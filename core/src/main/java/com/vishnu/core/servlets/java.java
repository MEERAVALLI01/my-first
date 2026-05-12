package com.vishnu.core.servlets;

import com.day.cq.replication.Replicator;
import com.day.cq.replication.ReplicationActionType;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(
    service = Servlet.class,
    property = {
        "sling.servlet.paths=/bin/publishPage",
        "sling.servlet.methods=POST"
    }
)
public class java extends SlingAllMethodsServlet {

    @Reference
    private Replicator replicator;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws IOException {

        String pagePath = request.getParameter("path");

        try {
            replicator.replicate(
                    request.getResourceResolver().adaptTo(javax.jcr.Session.class),
                    ReplicationActionType.ACTIVATE,
                    pagePath
            );

            response.getWriter().write("Page published successfully: " + pagePath);

        } catch (Exception e) {
            response.getWriter().write("Error while publishing: " + e.getMessage());
        }
    }
}