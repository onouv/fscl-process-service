package org.fscl.process.application;

import org.fscl.core.adapters.driven.web.EntityDto;
import org.fscl.process.domain.Function;

public class FunctionApiMapper {

	public static EntityDto domain2Api(Function domain) {
		return new EntityDto(domain.getEntityId(), domain.getName(), domain.getDescription());
	}
}
