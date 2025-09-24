package org.fscl.process.function.adapters.driving.persistence;

import org.fscl.core.domain.entity.id.FsclEntityId;

public class PersistenceException extends RuntimeException {

    public final FsclEntityId id;

    public PersistenceException(FsclEntityId id) {
        super("unknown persistence handling error");
        this.id = id;
    }
}
