package org.fscl.process.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.fscl.core.application.EntityIdJpaMapper;
import org.fscl.core.application.EntityRecord;
import org.fscl.core.application.messaging.FunctionEventMapper;
import org.fscl.core.commons.entity.FsclEntityData;
import org.fscl.core.commons.entity.FsclEntityId;
import org.fscl.core.domain.events.FsclDomainEvent;
import org.fscl.core.ports.driving.messaging.FunctionMessagingPort;
import org.fscl.core.ports.driving.messaging.MessagingException;
import org.fscl.core.ports.lifecycle.FsclEntityState;
import org.fscl.process.adapters.driving.persistence.FunctionJpaDto;
import org.fscl.process.adapters.driving.persistence.FunctionRepository;
import org.fscl.process.domain.Function;
import org.fscl.process.ports.driven.FunctionLifeCycleService;
import org.fscl.process.ports.driven.web.FunctionCreationResult;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FunctionLifecycleServiceImpl implements FunctionLifeCycleService {

	@Inject
	Event<FsclDomainEvent> domainEvent;

	@Inject
	FunctionMessagingPort messagingPort;

	@Inject
	FunctionEventMapper eventMapper;

	@Inject
	FunctionRepository functionRepo;

	@Inject
	FunctionJpaMapper dataMapper;

	@Override
	@Transactional
	public EntityRecord createFunction(FsclEntityId id, String name, String description) throws MessagingException {

		// TODO: validate incoming id

		FunctionCreationResult result = this.create(id, name, description);

		for (FsclDomainEvent evt : result.events()) {
			Log.info("Firing event: " + evt.toString());

			this.messagingPort.publish(this.eventMapper.outwards(evt));
		}
		return new EntityRecord(result.function().getEntityId(), result.state());
	}

	@Override
	public List<FsclEntityData> getAllForProject(String projectId) throws Exception {
		final List<FunctionJpaDto> dtos = this.functionRepo.findAllForProject(projectId);

		final List<Function> functions = dtos.stream()
			.map(dto -> this.dataMapper.inwards(dto))
			.collect(Collectors.toList());

		return functions.stream().map(f -> FunctionApiMapper.domain2Api(f)).collect(Collectors.toList());
	}

	public FunctionCreationResult create(FsclEntityId id, String name, String description) {
		Optional<FunctionJpaDto> viewFunction = functionRepo.findById(EntityIdJpaMapper.INSTANCE.outwards(id));

		if (viewFunction.isPresent()) {
			return this.handlePreExisting(this.dataMapper.inwards(viewFunction.get()));
		}

		return this.handleNew(id, name, description);
	}

	private FunctionCreationResult handlePreExisting(Function function) {
		Log.info(String.format("function %s found preexisting n view.", function.getEntityId().toString()));
		return new FunctionCreationResult(function, FsclEntityState.PreexistingInView, new ArrayList<>());
	}

	private FunctionCreationResult handleNew(FsclEntityId id, String name, String description) {
		Function newFunction = Function.builder().entityId(id).name(name).description(description).build();
		this.functionRepo.persist(dataMapper.outwards(newFunction));
		Log.info(String.format("created new function %s in view.", newFunction.getEntityId().toString()));

		return newFunction.created();
	}
}
