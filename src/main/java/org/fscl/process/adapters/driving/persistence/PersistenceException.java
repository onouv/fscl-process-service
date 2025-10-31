package org.fscl.process.adapters.driving.persistence;

import org.fscl.core.commons.entity.FsclEntityId;

public class PersistenceException extends RuntimeException {

    public final FsclEntityId id;

    public PersistenceException(FsclEntityId id) {
        super("unknown persistence handling error");
        this.id = id;
    }
}
