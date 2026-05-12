package com.vishnu.core.servlets;



import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.replication.Replicator;
import com.day.cq.replication.ReplicationActionType;

import org.apache.sling.api.resource.ResourceResolver;

@Component(service = Servlet.class,
property = {
"sling.servlet.paths=/bin/publishpage",
"sling.servlet.methods=GET"
})
public class PublishPageServlet extends SlingAllMethodsServlet {

@Reference
Replicator replicator;

@Override
protected void doGet(SlingHttpServletRequest request,
                     SlingHttpServletResponse response)
throws IOException {

try {

String pagePath = "/content/aemreactlearning/us/en/newpage"; // page path

ResourceResolver resolver = request.getResourceResolver();

replicator.replicate(
resolver.adaptTo(javax.jcr.Session.class),
ReplicationActionType.ACTIVATE,
pagePath
);

response.getWriter().write("Page published successfully");

} catch (Exception e) {

response.getWriter().write("Error while publishing page");

}

}
}
