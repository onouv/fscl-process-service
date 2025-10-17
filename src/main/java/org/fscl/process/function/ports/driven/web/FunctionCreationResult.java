package org.fscl.process.function.ports.driven.web;

import org.fscl.core.domain.events.FsclDomainEvent;
import org.fscl.core.ports.web.driven.lifecycle.FsclEntityState;
import org.fscl.process.function.domain.Function;

import java.util.List;

public record FunctionCreationResult(
        Function function,
        FsclEntityState state,
        List<FsclDomainEvent> events) {
}
