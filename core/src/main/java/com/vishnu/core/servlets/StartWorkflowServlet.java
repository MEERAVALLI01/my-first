package com.vishnu.core.servlets;


import javax.servlet.Servlet;

import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.osgi.service.component.annotations.Component;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.model.WorkflowModel;
import com.adobe.granite.workflow.exec.WorkflowData;

import org.apache.sling.api.resource.ResourceResolver;

@Component(
    service = Servlet.class,
    property = {
        "sling.servlet.paths=/bin/startWorkflow",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET
    }
)
public class StartWorkflowServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {

        try {
            String payloadPath = request.getParameter("path"); // page path

            ResourceResolver resolver = request.getResourceResolver();
            WorkflowSession workflowSession = resolver.adaptTo(WorkflowSession.class);

            // Workflow Model Path
            String modelPath = "/var/workflow/models/step-workflow";

            WorkflowModel model = workflowSession.getModel(modelPath);

            // Create Workflow Data
          WorkflowData data = workflowSession.newWorkflowData("JCR_PATH", payloadPath);

            // Start Workflow
            workflowSession.startWorkflow(model, data);

            response.getWriter().write("Workflow Started for: " + payloadPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
