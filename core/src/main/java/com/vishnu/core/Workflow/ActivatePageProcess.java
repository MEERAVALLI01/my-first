package com.vishnu.core.Workflow;

import javax.jcr.Session;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;

import com.adobe.granite.workflow.metadata.MetaDataMap;

import com.day.cq.replication.Replicator;
import com.day.cq.replication.ReplicationActionType;

@Component(
        service = WorkflowProcess.class,
        property = {
                "process.label=Activate Page Process"
        }
)
public class ActivatePageProcess implements WorkflowProcess {

    @Reference
    private Replicator replicator;

    public void execute1(WorkItem workItem,
                        WorkflowSession workflowSession,
                        MetaDataMap metaDataMap)
            throws WorkflowException {

        try {

            String path = workItem.getWorkflowData()
                                  .getPayload()
                                  .toString();

            replicator.replicate(
                    workflowSession.adaptTo(Session.class),
                    ReplicationActionType.ACTIVATE,
                    path
            );

        } catch (Exception e) {
            throw new WorkflowException(e);
        }
    }

    @Override
    public void execute(WorkItem arg0, WorkflowSession arg1, MetaDataMap arg2) throws WorkflowException {
        // TODO Auto-generated method stub 
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}