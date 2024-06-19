package org.fscl.process.service.function.adapters.upstream.web;

import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.process.service.function.domain.Function;

public record FunctionDto(FsclEntityId id, String name, String description) {
    public static FunctionDto of(Function f) {
        return new FunctionDto(f.getEntityId(), f.getName(), f.getDescription());
    }

}
