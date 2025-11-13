package org.fscl.process.ports.driven.web;

import java.util.List;

import org.fscl.core.domain.events.FsclDomainEvent;
import org.fscl.core.ports.lifecycle.ResourceDistributionState;
import org.fscl.process.domain.Function;

public record FunctionCreationResult(Function function, ResourceDistributionState state, List<FsclDomainEvent> events) {
}
