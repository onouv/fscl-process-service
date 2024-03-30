package org.fscl.process.service.function.domain;

import lombok.experimental.SuperBuilder;

import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.core.domain.function.FunctionBase;

@SuperBuilder
public class Function extends FunctionBase {

    protected Function(String code, String project, Function parent, String name, String description) {
        super(new FsclEntityId(code, project), parent, name, description);
    }

}
