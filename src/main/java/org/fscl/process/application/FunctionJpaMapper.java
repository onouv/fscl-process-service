package org.fscl.process.application;

import org.fscl.core.adapters.driving.persistence.entity.EntityIdJpaDto;
import org.fscl.core.application.EntityIdJpaMapper;
import org.fscl.core.commons.entity.FsclEntityId;
import org.fscl.process.adapters.driving.persistence.FunctionJpaDto;
import org.fscl.process.domain.Function;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FunctionJpaMapper {

	private final EntityIdJpaMapper idMapper = EntityIdJpaMapper.INSTANCE;

	public FunctionJpaDto outwards(Function domain) {
		return FunctionJpaDto.builder().entityId(idMapper.outwards(domain.getEntityId())).build();
	}

	public Function inwards(FunctionJpaDto data) {
		EntityIdJpaDto id = data.getEntityId();
		return Function.builder()
			.entityId(new FsclEntityId(id.getProject(), id.getCode()))
			.name(data.getName())
			.description(data.getDescription())
			.build();
	}

}
