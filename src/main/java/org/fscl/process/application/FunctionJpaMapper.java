package org.fscl.process.application;

import org.fscl.core.adapters.driving.persistence.entity.EntityIdJpaDto;
import org.fscl.core.application.EntityIdJpaMapper;
import org.fscl.core.commons.entity.FsclEntityId;
import org.fscl.process.adapters.driving.persistence.FunctionJpaDto;
import org.fscl.process.domain.Function;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = EntityIdJpaMapper.class)
public abstract class FunctionJpaMapper {

	public static final FunctionJpaMapper INSTANCE = Mappers.getMapper(FunctionJpaMapper.class);

	public abstract FunctionJpaDto outwards(Function domain);

	public Function inwards(FunctionJpaDto data) {
		return Function.builder()
			// .entityId(this.entityIdInwards(data.getEntityId()))
			.entityId(new FsclEntityId(data.getProject(), data.getCode()))
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

		return new FsclEntityId(project, code);
	}
}
