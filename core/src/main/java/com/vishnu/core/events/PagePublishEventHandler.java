package com.vishnu.core.events;



import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
        service = EventHandler.class,
        property = {
                "event.topics=com/day/cq/replication"
        }
)
public class PagePublishEventHandler implements EventHandler {

    private static final Logger log = LoggerFactory.getLogger(PagePublishEventHandler.class);

    @Override
    public void handleEvent(Event event) {

        String path = (String) event.getProperty("path");
        String type = (String) event.getProperty("type");

        if ("ACTIVATE".equals(type)) {
            log.info("Page Published Successfully: {}", path);
        }
    }
}
