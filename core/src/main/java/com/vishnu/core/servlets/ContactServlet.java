package com.vishnu.core.servlets;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/contactcrud")
public class ContactServlet extends SlingAllMethodsServlet {

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        ResourceResolver resolver = request.getResourceResolver();

        try {
            Resource parent = resolver.getResource("/content/crx/de/contactdata");

            Map<String, Object> map = new HashMap<>();
            map.put("name", name);
            map.put("email", email);
            map.put("subject", subject);
            map.put("message", message);

            resolver.create(parent, "node" + System.currentTimeMillis(), map);

            resolver.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
