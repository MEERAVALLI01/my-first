package com.vishnu.core.servlets;

import java.io.IOException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;

@Component(service = Servlet.class,
property = {
"sling.servlet.paths=/bin/demoservice",
"sling.servlet.methods=GET"
})
public class DemoServlet extends SlingSafeMethodsServlet {

@Override
protected void doGet(SlingHttpServletRequest request,
                     SlingHttpServletResponse response)
throws IOException {

response.getWriter().write("Hello from AEM Servlet");
}
}