package com.vishnu.core.events;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import com.day.cq.replication.Replicator;
import com.day.cq.replication.ReplicationActionType;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import java.util.HashMap;
import java.util.Map;

@Component(
        service = EventHandler.class,
        property = {
                "event.topics=org/apache/sling/api/resource/Resource/ADDED"
        }
)
public class PageEventHandler implements EventHandler {

    @Reference
    private Replicator replicator;

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public void handleEvent(Event event) {

        String path = (String) event.getProperty("path");

        try {

            Map<String, Object> param = new HashMap<>();
            param.put(ResourceResolverFactory.SUBSERVICE, "phone");

            ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param);

            replicator.replicate(
                    resolver.adaptTo(javax.jcr.Session.class),
                    ReplicationActionType.ACTIVATE,
                    path
            );

            System.out.println("Page published: " + path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
