package com.vishnu.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import org.json.JSONObject;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/cfdata")
public class ContentFragmentServlet extends org.apache.sling.api.servlets.SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getParameter("path");
        response.setContentType("application/json");

        if (path == null || path.isEmpty()) {
            response.getWriter().write("{\"error\":\"Path is required\"}");
            return;
        }

        Resource resource = request.getResourceResolver().getResource(path);

        if (resource == null) {
            response.getWriter().write("{\"error\":\"Invalid Path\"}");
            return;
        }

        // Navigate to CF data node
        Resource dataResource = resource.getChild("jcr:content/data/master");

        if (dataResource == null) {
            response.getWriter().write("{\"error\":\"No data found\"}");
            return;
        }

        ValueMap vm = dataResource.getValueMap();

        Map<String, Object> jsonMap = new HashMap<>();

        for (String key : vm.keySet()) {

           
            if (key.startsWith("jcr:") || key.startsWith("cq:") || key.contains("@")) {
                continue;
            }

       
            Object value = vm.get(key);

            
            if (value != null) {
                jsonMap.put(key, value.toString());
            }
        }

        JSONObject jsonObject = new JSONObject(jsonMap);
        response.getWriter().write(jsonObject.toString());
    }
}