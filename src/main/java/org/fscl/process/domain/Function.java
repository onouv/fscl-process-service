package org.fscl.process.domain;

import java.util.ArrayList;
import java.util.List;

import org.fscl.core.commons.entity.ResourceId;
import org.fscl.core.domain.entity.FsclFunction;
import org.fscl.core.domain.events.FsclDomainEvent;
import org.fscl.core.domain.events.FunctionCreatedEvent;
import org.fscl.core.ports.lifecycle.FsclEntityState;
import org.fscl.process.ports.driven.web.FunctionCreationResult;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Function extends FsclFunction {

	public Function(ResourceId id, Function parent, String name, String description) {
		super(id, parent, name, description);
	}

	public FunctionCreationResult created() {
		List<FsclDomainEvent> events = new ArrayList<>();
		events.add(new FunctionCreatedEvent("process", this));
		return new FunctionCreationResult(this, FsclEntityState.CreatedInView, events);
	}
}
