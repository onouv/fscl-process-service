package org.fscl.process.ports.driven;

import java.util.List;

import org.fscl.core.application.EntityRecord;
import org.fscl.core.commons.entity.FsclEntityData;
import org.fscl.core.commons.entity.FsclEntityId;


public interface FunctionLifeCycleService {

    EntityRecord createFunction(FsclEntityId id, String name, String description) throws Exception;
    
    List<FsclEntityData> getAllForProject(String projectId) throws Exception;

}
