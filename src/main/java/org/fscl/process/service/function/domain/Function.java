package org.fscl.process.service.function.domain;

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
//@Table(name = "function")
public class Function extends FsclEntity<Function> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    protected Function(String code, String project, Function parent, String name, String description) {
        super(new FsclEntityId(code, project), parent, name, description);
    }

    public FunctionCreationResult created() {
        List<FsclDomainEvent> events = new ArrayList<>();
        events.add(new FunctionCreatedEvent(this.identifier, this.name, this.description));
        return new FunctionCreationResult(this.identifier, FsclEntityState.CreatedInView, events);
    }
}
