package org.fscl.process.application;

import org.fscl.core.commons.ResourceId;
import org.fscl.core.commons.ResourceIdDataDto;
import org.fscl.core.commons.ResourceIdDataMapper;
import org.fscl.process.adapters.driving.persistence.FunctionDataDto;
import org.fscl.process.domain.Function;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FunctionDataMapper {

	@Inject
	private ResourceIdDataMapper idMapper;

	public FunctionDataDto outwards(Function domain) {
		return FunctionDataDto.builder()
			.entityId(idMapper.outwards(domain.getResourceId()))
			.name(domain.getName())
			.description(domain.getDescription())
			.build();
	}

	public Function inwards(FunctionDataDto data) {
		ResourceIdDataDto id = data.getEntityId();
		return Function.builder()
			.resourceId(new ResourceId(id.getProject(), id.getCode()))
			.name(data.getName())
			.description(data.getDescription())
			.build();
	}

}
