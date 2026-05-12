package com.vishnu.core.Workflow;


import com.day.cq.replication.*;
import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.*;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.*;
import com.adobe.granite.workflow.metadata.MetaDataMap;

import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;

@Component(service = WorkflowProcess.class,
        property = {"process.label=Replicate After Page Move"})
public class PreviewReplicationStep implements WorkflowProcess {

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Reference
    private Replicator replicator;

    public void execute1(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap args) {

        String path = workItem.getWorkflowData().getPayload().toString();

        Map<String, Object> params = new HashMap<>();
        params.put(ResourceResolverFactory.SUBSERVICE, "replication-service");

        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(params)) {

            // ✅ Step 1: Enable Preview Agent
            String agentPath = "/etc/replication/agents.author/preview-agent/jcr:content";
            Resource resource = resolver.getResource(agentPath);

            if (resource != null) {
                ModifiableValueMap mvm = resource.adaptTo(ModifiableValueMap.class);
                if (mvm != null) {
                    mvm.put("enabled", true);
                    resolver.commit();
                }
            }

            // ✅ Step 2: Replicate Page to Preview
            Session session = resolver.adaptTo(Session.class);

            ReplicationOptions options = new ReplicationOptions();
            options.setFilter(agent -> agent.getId().equals("preview-agent"));

            replicator.replicate(session, ReplicationActionType.ACTIVATE, path, options);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(WorkItem arg0, WorkflowSession arg1, MetaDataMap arg2) throws WorkflowException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
