package com.vishnu.core.schedulers;


import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service = Runnable.class,
property = {
    Scheduler.PROPERTY_SCHEDULER_EXPRESSION + "=0/5 * * * * ?"
})
    public class DemoScheduler implements Runnable {

  private static final Logger log = LoggerFactory.getLogger(DemoScheduler .class);

@Override
public void run() {
    log.info("Scheduler executed successfully");
}
       
    }
       

 


