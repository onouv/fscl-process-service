package org.fscl.process.service.function.appservices;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.fscl.core.appservices.EntityRecord;
import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.core.domain.events.FsclDomainEvent;
import org.fscl.process.service.function.domain.CreationService;
import org.fscl.process.service.function.ports.upstream.web.FunctionCreationResult;
import org.fscl.process.service.function.ports.upstream.web.FunctionLifeCycleService;

@ApplicationScoped
public class FunctionLifecycleServiceImpl implements FunctionLifeCycleService {

    @Inject
    Event<FsclDomainEvent> event;

    @Inject
    CreationService domainService;

    @Override
    @Transactional
    public EntityRecord createFunction(FsclEntityId id, String name, String description) {

        FunctionCreationResult result = domainService.create(id, name, description);

        for(FsclDomainEvent evt: result.events()) {
            this.event.fire(evt);
        }
        return new EntityRecord(result.function().getEntityId(), result.state());
    }
}
