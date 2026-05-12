package com.vishnu.core.servlets;


import org.apache.sling.api.servlets.*;
import org.apache.sling.api.*;

import javax.servlet.Servlet;

import org.osgi.service.component.annotations.*;

import com.vishnu.core.Services.PageReviewService;

@Component(service = Servlet.class,
           property = {
               "sling.servlet.paths=/bin/review-pages",
               "sling.servlet.methods=GET"
           })
public class ReviewServlet extends SlingAllMethodsServlet {

    @Reference
    private PageReviewService service;

    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response) {

        try {
            String path = request.getParameter("path");

            int result = service.reviewPages(request.getResourceResolver(), path);

            response.getWriter().write("Reviewed pages count: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}