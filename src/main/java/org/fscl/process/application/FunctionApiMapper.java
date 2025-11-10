package org.fscl.process.application;

import org.fscl.core.commons.entity.FsclEntityData;
import org.fscl.process.domain.Function;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FunctionApiMapper {

	public static FsclEntityData domain2Api(Function domain) {
		return new FsclEntityData(domain.getEntityId(), domain.getName(), domain.getDescription());
	}
}
