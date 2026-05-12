package com.vishnu.core.events;





import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

@Component(
    service = EventHandler.class,
    property = {
        "event.topics=org/apache/sling/api/resource/Resource/ADDED"
    }
)

public class SimpleEventHandler implements EventHandler {

    @Override
    public void handleEvent(Event event) {

        String path = (String) event.getProperty("path");

        System.out.println("Resource Created at Path: " + path);

    }
}
