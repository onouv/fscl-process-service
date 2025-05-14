package org.fscl.process.function.ports.upstream.web;

import org.fscl.core.appservices.EntityRecord;
import org.fscl.core.domain.entity.id.FsclEntityId;


public interface FunctionLifeCycleService {

    EntityRecord createFunction(FsclEntityId id, String name, String description) throws Exception;

}
