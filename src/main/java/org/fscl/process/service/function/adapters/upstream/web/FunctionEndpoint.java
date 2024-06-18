package org.fscl.process.service.function.adapters.upstream.web;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.fscl.core.adapters.upstream.web.lifecycle.*;
import org.fscl.core.appservices.EntityRecord;
import org.fscl.core.ports.upstream.web.lifecycle.FsclEntityState;
import org.fscl.process.service.function.adapters.downstream.persistence.FunctionRepository;
import org.fscl.process.service.function.ports.upstream.web.FunctionCreationResult;
import org.fscl.process.service.function.ports.upstream.web.FunctionLifeCycleService;
import org.fscl.process.service.function.domain.Function;
import org.jboss.resteasy.reactive.RestResponse;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

@Path("/fscl/v2/process/function")
public class FunctionEndpoint {

    @Inject
    FunctionLifeCycleService lifeCycleService;

    @Inject
    FunctionRepository repository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/lifesign")
    public RestResponse<String> getLifeSign() {
        return RestResponse.ok("fscl process service function endpoint is alive.");
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public RestResponse<EntityExistingResponseDto> createFunction(CreateEntityRequestDto dto) throws Exception {

        EntityRecord result = lifeCycleService.createFunction(dto.getId(), dto.getName(), dto.getDescription());
        FsclEntityState state = result.state();
        switch(state) {
            case CreatedInView:
                return RestResponse.ok(new EntityExistingResponseDto(
                        result.id(),
                        state));
            case PreexistingInView:
                return RestResponse.status(RestResponse.Status.CONFLICT, new EntityExistingResponseDto(
                        result.id(),
                        state));
            default:
                throw new Exception(
                        String.format("function was created with unexpected state: %s",
                        state));
        }
    }

    // TODO: write endpoint
    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<List<EntityExistingResponseDto>> getAllFunctions() {
        PanacheQuery<Function> functions = repository.findAll();

    }

     */
}
