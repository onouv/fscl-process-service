package org.fscl.process.service.function.ports.upstream.web;

import jakarta.enterprise.context.ApplicationScoped;
import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.process.service.function.domain.Function;


public interface FunctionLifeCycleService {

    Function createFunction(FsclEntityId id, String name, String description);

}
