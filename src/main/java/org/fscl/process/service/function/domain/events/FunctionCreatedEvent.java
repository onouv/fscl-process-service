package org.fscl.process.service.function.domain.events;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.core.domain.events.FsclDomainEvent;

@EqualsAndHashCode(callSuper = false)
@Value
public class FunctionCreatedEvent extends FsclDomainEvent {
    FsclEntityId id;
    String name;
    String description;
}
