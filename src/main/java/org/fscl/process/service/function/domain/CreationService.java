package org.fscl.process.service.function.domain;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.core.domain.function.FunctionBase;
import org.fscl.core.ports.upstream.web.lifecycle.FsclEntityState;
import org.fscl.process.service.function.adapters.downstream.persistence.FunctionRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CreationService {

    @Inject
    FunctionRepo repo;

    public Function create(FsclEntityId id, String name, String description) {
        Optional<Function> viewFunction = repo.findById(id);
        Function f;
        if (viewFunction.isPresent()) {
            f = viewFunction.get();
            f.setState(FsclEntityState.PreexistingInView);

            return f;
        }

        f = Function.builder()
                .identifier(id)
                .name(name)
                .description(description)
                .state(FsclEntityState.CreatedInView)
                .build();
        this.repo.persist(f);

        return f;
    }
}
