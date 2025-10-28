package org.fscl.process.ports.driven;

import java.util.List;

import org.fscl.core.adapters.driven.web.EntityDto;
import org.fscl.core.application.EntityRecord;
import org.fscl.core.domain.entity.id.FsclEntityId;


public interface FunctionLifeCycleService {

    EntityRecord createFunction(FsclEntityId id, String name, String description) throws Exception;
    
    List<EntityDto> getAllForProject(String projectId) throws Exception;

}
