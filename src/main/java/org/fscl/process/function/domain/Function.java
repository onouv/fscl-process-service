package org.fscl.process.function.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import org.fscl.core.domain.entity.FsclEntity;
import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.core.domain.events.FsclDomainEvent;
import org.fscl.core.ports.upstream.web.lifecycle.FsclEntityState;
import org.fscl.process.service.function.domain.events.FunctionCreatedEvent;
import org.fscl.process.service.function.ports.upstream.web.FunctionCreationResult;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Entity
public class Function extends FsclEntity<Function> {

    protected Function(String code, String project, Function parent, String name, String description) {
        super(new FsclEntityId(code, project), parent, name, description);
    }

    public FunctionCreationResult created() {
        List<FsclDomainEvent> events = new ArrayList<>();
        events.add(FunctionCreatedEvent.of(this));
        return new FunctionCreationResult(this, FsclEntityState.CreatedInView, events);
    }
}
