package org.fscl.process.service.function.appservices;

import org.fscl.core.ports.upstream.web.id.FsclEntityIdDto;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.fscl.process.service.function.ports.upstream.web.FunctionLifeCycleService;
import org.springframework.stereotype.Service;

@Service
public class FunctionLifecycleServiceImpl implements FunctionLifeCycleService {

    private CommandGateway commandGateway;

    public FsclEntityIdDto FunctionLifeCycleServiceImpl(CommandGateway gateway) {
        this.commandGateway = gateway;
        return null;
    }

    @Override
    public FsclEntityIdDto createFunction(FsclEntityIdDto dto) {
        return null;
    }
}
