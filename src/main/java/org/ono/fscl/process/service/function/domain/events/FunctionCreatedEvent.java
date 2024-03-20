package org.ono.fscl.process.service.function.domain.events;

import ono.fscl.core.domain.function.FunctionCode;

public record FunctionCreatedEvent(
    //FsclEntityId<FunctionCode> id,
    String name,
    String description) {
}
