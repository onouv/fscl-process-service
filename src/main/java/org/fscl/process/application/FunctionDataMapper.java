package org.fscl.process.application;

import org.fscl.core.adapters.driving.persistence.entity.EntityIdJpaDto;
import org.fscl.core.commons.entity.FsclEntityId;
import org.fscl.process.adapters.driving.persistence.FunctionJpaDto;
import org.fscl.process.domain.Function;
import org.mapstruct.factory.Mappers;

public abstract class FunctionDataMapper {

	public static final FunctionDataMapper INSTANCE = Mappers.getMapper(FunctionDataMapper.class);

	public abstract FunctionJpaDto outwards(Function domain);

	public Function inwards(FunctionJpaDto data) {
		return Function.builder()
			.entityId(this.entityIdInwards(data.getEntityId()))
			.name(data.getName())
			.description(data.getDescription())
			.build();
	}

	protected FsclEntityId entityIdInwards(EntityIdJpaDto entityIdJpaDto) {
		if (entityIdJpaDto == null) {
			return null;
		}

		String project = null;
		String code = null;

		project = entityIdJpaDto.getProject();
		code = entityIdJpaDto.getCode();

		FsclEntityId fsclEntityId = new FsclEntityId(project, code);

		return fsclEntityId;
	}
}
