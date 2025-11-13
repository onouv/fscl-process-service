package org.fscl.process.adapters.driving.persistence;

import org.fscl.core.commons.ResourceId;

public class PersistenceException extends RuntimeException {

	public final ResourceId id;

	public PersistenceException(ResourceId id) {
		super("unknown persistence handling error");
		this.id = id;
	}
}
