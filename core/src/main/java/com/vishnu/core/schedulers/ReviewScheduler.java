package com.vishnu.core.schedulers;

import org.osgi.service.component.annotations.*;
import org.apache.sling.commons.scheduler.Scheduler;

@Component(service = Runnable.class, immediate = true)
public class ReviewScheduler implements Runnable {

    @Reference
    private Scheduler scheduler;

    private static final String JOB_NAME = "my-review-scheduler";

    @Activate
    protected void activate() {
        // Schedule job
        scheduler.schedule(this, scheduler.EXPR("0 0 0/5 * * ?").name(JOB_NAME));
    }

    @Deactivate
    protected void deactivate() {
        // 🔥 This is IMPORTANT
        scheduler.unschedule(JOB_NAME);
    }

    @Override
    public void run() {
        System.out.println("Scheduler running...");
    }
}