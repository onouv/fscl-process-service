package org.fscl.process.function.adapters.web.driven;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import org.fscl.core.adapters.driven.web.*;
import org.fscl.core.application.EntityRecord;
import org.fscl.core.ports.web.driven.lifecycle.FsclEntityState;
import org.fscl.process.function.ports.driven.web.FunctionLifeCycleService;
import org.jboss.resteasy.reactive.RestResponse;
import java.util.List;
import java.util.stream.Collectors;

@Path("/fscl/v2/process/function")
public class FunctionEndpoint {

    @Inject
    FunctionLifeCycleService lifeCycleService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/lifesign")
    public RestResponse<String> getLifeSign() {
        return RestResponse.ok("fscl process service function endpoint is alive.");
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public RestResponse<EntityExistingResponseDto>
    createFunction(EntityDto dto) throws Exception {

        EntityRecord result = lifeCycleService
            .createFunction(dto.getId(), dto.getName(), dto.getDescription());
        FsclEntityState state = result.state();
        switch(state) {
            case CreatedInView:
                return RestResponse.ok(new EntityExistingResponseDto(
                        result.id(),
                        state));
            case PreexistingInView:
                return RestResponse.status(
                    RestResponse.Status.CONFLICT,
                    new EntityExistingResponseDto(
                        result.id(),
                        state));
            default:
                throw new Exception(
                        String.format("function was created with unexpected state: %s",
                        state));
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/by-project/{projectId}")
    public RestResponse<List<EntityDto>>
    getAllFunctions(@PathParam("projectId") String projectId) throws Exception {
        List<EntityDto> functions = this.lifeCycleService.getAllForProject(projectId);

        return RestResponse.ok(functions);
    }
}
