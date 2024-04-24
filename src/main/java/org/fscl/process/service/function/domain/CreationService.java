package org.fscl.process.service.function.domain;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;

import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.core.ports.upstream.web.lifecycle.FsclEntityState;
import org.fscl.process.service.function.adapters.downstream.persistence.FunctionRepository;

@ApplicationScoped
public class CreationService {

    @Inject
    FunctionRepository repo;

    public Function create(FsclEntityId id, String name, String description) {
        Optional<Function> viewFunction = repo.findById(id);

        if (viewFunction.isPresent()) {
            Function preexistingFunction = viewFunction.get();
            preexistingFunction.setState(FsclEntityState.PreexistingInView);

            return preexistingFunction;
        }

        Function newFunction = Function.builder()
                .entityId(id)
                .name(name)
                .description(description)
                .state(FsclEntityState.CreatedInView)
                .build();
        this.repo.persist(newFunction);

        return newFunction;
    }
}
