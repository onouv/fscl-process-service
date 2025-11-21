package org.fscl.process.ports.driven;

import java.util.List;

import org.fscl.core.application.ResourceRecord;
import org.fscl.core.commons.ResourceData;
import org.fscl.core.commons.ResourceId;

public interface FunctionLifeCycleService {

	ResourceRecord createFunction(ResourceId id, String name, String description) throws Exception;

	List<ResourceData> getAllForProject(String projectId) throws Exception;

}
