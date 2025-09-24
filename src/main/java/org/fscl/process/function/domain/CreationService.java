package org.fscl.process.function.domain;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Optional;

import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.core.ports.upstream.web.lifecycle.FsclEntityState;
import org.fscl.process.function.adapters.driving.persistence.FunctionRepository;
import org.fscl.process.function.ports.driven.web.FunctionCreationResult;

@ApplicationScoped
public class CreationService {

    @Inject
    FunctionRepository repo;

    public FunctionCreationResult create(FsclEntityId id, String name, String description) {
        Optional<Function> viewFunction = repo.findById(id);

        if (viewFunction.isPresent()) {
            return this.handlePreExisting(viewFunction.get());
        }

        return this.handleNew(id, name, description);
    }

    private FunctionCreationResult handlePreExisting(Function function) {
        Log.info(String.format("function %s found preexisting n view.", function.getEntityId().toString()));
        return new FunctionCreationResult(
                function,
                FsclEntityState.PreexistingInView,
                new ArrayList<>());
    }

    private FunctionCreationResult handleNew(FsclEntityId id, String name, String description) {
        Function newFunction = Function.builder()
                .project(id.project())
                .code(id.code())
                .name(name)
                .description(description)
                .build();
        this.repo.persist(newFunction);
        Log.info(String.format("created new function %s in view.", newFunction.getEntityId().toString()));

        return newFunction.created();
    }
}
