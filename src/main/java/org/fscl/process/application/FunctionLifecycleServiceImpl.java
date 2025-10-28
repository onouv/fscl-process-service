package org.fscl.process.application;

import io.quarkus.logging.Log;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.fscl.core.adapters.driven.web.EntityDto;
import org.fscl.core.application.EntityRecord;
import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.core.domain.events.FsclDomainEvent;
import org.fscl.core.ports.web.driven.lifecycle.FsclEntityState;
import org.fscl.process.adapters.driving.persistence.FunctionRepository;
import org.fscl.process.domain.Function;
import org.fscl.process.ports.driven.FunctionLifeCycleService;
import org.fscl.process.ports.driven.web.FunctionCreationResult;

@ApplicationScoped
public class FunctionLifecycleServiceImpl implements FunctionLifeCycleService {

    @Inject
    Event<FsclDomainEvent> domainEvent;
    
    @Inject
    FunctionRepository functionRepo;

    @Override
    @Transactional
    public EntityRecord createFunction(FsclEntityId id, String name, String description) {

        FunctionCreationResult result = this.create(id, name, description);

        for(FsclDomainEvent evt: result.events()) {
            Log.info("Firing event: " + evt.toString());
            // TODO: messaging event classes should be in adapters layer, provide proper mappers as well
            //this.domainEvent.fire(evt);
        }
        return new EntityRecord(result.function().getEntityId(), result.state());
    }
    
    @Override
    public List<EntityDto> getAllForProject(String projectId) throws Exception {
    	List<EntityDto> functions = this.functionRepo.findAllForProject(projectId)
                .stream()
                .map(f -> FunctionApiMapper.domain2Api(f))
                .collect(Collectors.toList());
    	
    	return functions;    	
    }
    
    public FunctionCreationResult create(FsclEntityId id, String name, String description) {
        Optional<Function> viewFunction = functionRepo.findById(id);

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
        this.functionRepo.persist(newFunction);
        Log.info(String.format("created new function %s in view.", newFunction.getEntityId().toString()));

        return newFunction.created();
    }
}
