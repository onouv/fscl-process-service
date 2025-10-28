package org.fscl.process.ports.driven.web;

import org.fscl.core.domain.events.FsclDomainEvent;
import org.fscl.core.ports.web.driven.lifecycle.FsclEntityState;
import org.fscl.process.domain.Function;

import java.util.List;

public record FunctionCreationResult(
        Function function,
        FsclEntityState state,
        List<FsclDomainEvent> events) {
}
