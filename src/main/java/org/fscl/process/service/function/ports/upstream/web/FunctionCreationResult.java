package org.fscl.process.service.function.ports.upstream.web;

import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.core.domain.events.FsclDomainEvent;
import org.fscl.core.ports.upstream.web.lifecycle.FsclEntityState;
import org.fscl.process.service.function.domain.Function;

import java.util.List;

public record FunctionCreationResult(
        Function function,
        FsclEntityState state,
        List<FsclDomainEvent> events) {
}
