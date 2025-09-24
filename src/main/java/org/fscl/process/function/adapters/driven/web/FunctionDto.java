package org.fscl.process.function.adapters.driven.web;

import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.process.function.domain.Function;

public record FunctionDto(FsclEntityId id, String name, String description) {
    public static FunctionDto of(Function f) {
        return new FunctionDto(f.getEntityId(), f.getName(), f.getDescription());
    }

}
