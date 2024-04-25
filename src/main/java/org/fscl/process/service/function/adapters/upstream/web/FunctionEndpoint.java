package org.fscl.process.service.function.adapters.upstream.web;

import jakarta.inject.Inject;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.fscl.core.adapters.upstream.web.lifecycle.*;
import org.fscl.core.ports.upstream.web.lifecycle.FsclEntityState;
import org.fscl.process.service.function.ports.upstream.web.FunctionLifeCycleService;
import org.fscl.process.service.function.domain.Function;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/fscl/v2/process/function")
public class FunctionEndpoint {

    @Inject
    FunctionLifeCycleService lifeCycleService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public RestResponse<EntityExistingResponseDto> createFunction(CreateEntityRequestDto dto) throws Exception {

        Function function = lifeCycleService.createFunction(dto.getId(), dto.getName(), dto.getDescription());
        FsclEntityState state = function.getState();
        switch(state) {
            case CreatedInView:
                return RestResponse.ok(new EntityExistingResponseDto(
                        function.getEntityId(),
                        state));
            case PreexistingInView:
                return RestResponse.status(RestResponse.Status.CONFLICT, new EntityExistingResponseDto(
                        function.getEntityId(),
                        state));
            default:
                throw new Exception(
                        String.format("function was created with unexpected state: %s",
                        state));
        }
    }
}
