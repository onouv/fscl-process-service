package org.fscl.process.application;

import org.fscl.core.commons.ResourceData;
import org.fscl.process.domain.Function;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FunctionApiMapper {

	public static ResourceData outwards(Function domain) {
		return new ResourceData(domain.getResourceId(), domain.getName(), domain.getDescription());
	}
}
