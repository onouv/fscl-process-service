package org.fscl.process.function.adapters.downstream.persistence;

import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.core.ports.upstream.web.lifecycle.FsclEntityState;

public class PersistenceException extends RuntimeException {

    public final FsclEntityId id;

    public PersistenceException(FsclEntityId id) {
        super("unknown persistence handling error");
        this.id = id;
    }
}
