package org.fscl.process.service.function.ports.upstream.web;

import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.core.domain.events.FsclDomainEvent;
import org.fscl.core.ports.upstream.web.lifecycle.FsclEntityState;

import java.util.List;

public record FunctionCreationResult(
        FsclEntityId id,
        FsclEntityState state,
        List<FsclDomainEvent> events) {
}
