package org.fscl.process.service.function.adapters.upstream.web;

import jakarta.inject.Inject;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.fscl.core.adapters.upstream.web.lifecycle.*;
import org.fscl.core.ports.upstream.web.lifecycle.FsclEntityState;
import org.fscl.process.service.function.ports.upstream.web.FunctionLifeCycleService;
import org.fscl.process.service.function.domain.Function;
import org.jboss.resteasy.reactive.RestQuery;
import org.jboss.resteasy.reactive.RestResponse;
import io.quarkus.logging.Log;
@Path("/fscl/v2/functions")
public class FunctionEndpoint {

    @Inject
    FunctionLifeCycleService lifeCycleService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public RestResponse<EntityExistingResponseDto> createFunction(CreateEntityRequestDto dto) throws Exception {
        System.out.println("Creating function...");

        Function function = lifeCycleService.createFunction(dto.getId(), dto.getName(), dto.getDescription());
        return RestResponse.ok(new EntityExistingResponseDto(
                function.getEntityId(),
                function.getState()));

    }
}
