package com.vishnu.core.Services;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.replication.Replicator;
import com.day.cq.replication.ReplicationActionType;

import org.apache.sling.api.resource.ResourceResolver;

@Component(service = PublishService.class)
public class PublishService {

    @Reference
    private Replicator replicator;

    public void publishPage(ResourceResolver resolver) {

        try {

            String pagePath = "/content/aemreactlearning/us/en/newpage";

            replicator.replicate(
                    resolver.adaptTo(javax.jcr.Session.class),
                    ReplicationActionType.ACTIVATE,
                    pagePath
            );

            System.out.println("Page Published: " + pagePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
