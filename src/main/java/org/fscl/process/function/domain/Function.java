package org.fscl.process.function.domain;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.core.domain.events.FsclDomainEvent;
import org.fscl.core.domain.function.FsclFunction;
import org.fscl.core.domain.function.FunctionCreatedEvent;
import org.fscl.core.ports.driven.web.lifecycle.FsclEntityState;
import org.fscl.process.function.ports.driven.web.FunctionCreationResult;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
public class Function extends FsclFunction {

    protected Function(String code, String project, Function parent, String name, String description) {
        super(new FsclEntityId(code, project), parent, name, description);
    }

    public FunctionCreationResult created() {
        List<FsclDomainEvent> events = new ArrayList<>();
        events.add(new FunctionCreatedEvent(this));
        return new FunctionCreationResult(this, FsclEntityState.CreatedInView, events);
    }
}
