package org.fscl.process.service.function.ports.upstream.web;

import org.fscl.core.ports.upstream.web.id.FsclEntityIdDto;

public interface FunctionLifeCycleService {

    /**
     * Create a single function entity as identified by the dto.
     * @param dto
     * @return the
     */
    public FsclEntityIdDto createFunction(FsclEntityIdDto dto);
}
