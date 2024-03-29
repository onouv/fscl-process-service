package org.fscl.process.service.function.domain.commands;

import ono.fscl.core.domain.entity.id.FsclEntityId;
import ono.fscl.core.domain.function.FunctionCode;

public record CreateFunctionCmd(
        FsclEntityId<FunctionCode> id,
        String name,
        String description
) {

}
