package org.fscl.process.function.application;

import org.fscl.core.adapters.driven.web.EntityDto;
import org.fscl.process.function.domain.Function;

public class FunctionApiMapper {

	public static EntityDto domain2Api(Function domain) {
		return new EntityDto(domain.getEntityId(), domain.getName(), domain.getDescription());
	}
}
