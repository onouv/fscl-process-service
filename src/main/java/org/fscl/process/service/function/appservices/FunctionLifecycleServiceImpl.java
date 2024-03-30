package org.fscl.process.service.function.appservices;

import jakarta.enterprise.context.ApplicationScoped;
import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.process.service.function.domain.Function;
import org.fscl.process.service.function.ports.upstream.web.FunctionLifeCycleService;

@ApplicationScoped
public class FunctionLifecycleServiceImpl implements FunctionLifeCycleService {

    @Override
    public Function createFunction(FsclEntityId id, String name, String description) {
        return Function.builder()
                .identifier(id)
                .name(name)
                .description(description)
                .build();
    }
}
