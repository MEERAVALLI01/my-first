package com.vishnu.core.Services;


import com.day.cq.replication.Replicator;
import com.day.cq.replication.ReplicationActionType;

import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Session;

@Component(service = ReplicationService.class)
public class ReplicationService {

    @Reference
    private Replicator replicator;

    public void publishPage(ResourceResolver resolver) {
        try {
          
            String pagePath = "/content/aemreactlearning/us/en/newpage/publish";

            Session session = resolver.adaptTo(Session.class);

            replicator.replicate(
                    session,
                    ReplicationActionType.ACTIVATE,
                    pagePath
            );


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}