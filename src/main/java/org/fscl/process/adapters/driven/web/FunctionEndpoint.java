package org.fscl.process.adapters.driven.web;

import java.util.List;

import org.fscl.core.adapters.driven.web.EntityExistingResponseDto;
import org.fscl.core.application.EntityRecord;
import org.fscl.core.commons.entity.FsclEntityData;
import org.fscl.core.ports.lifecycle.FsclEntityState;
import org.fscl.process.ports.driven.FunctionLifeCycleService;
import org.jboss.resteasy.reactive.RestResponse;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

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
	public RestResponse<EntityExistingResponseDto> createFunction(FsclEntityData dto) throws Exception {

		EntityRecord result = lifeCycleService.createFunction(dto.getEntityId(), dto.getName(), dto.getDescription());
		FsclEntityState state = result.state();
		switch (state) {
			case CreatedInView:
				return RestResponse.ok(new EntityExistingResponseDto(result.id(), state));
			case PreexistingInView:
				return RestResponse.status(RestResponse.Status.CONFLICT,
						new EntityExistingResponseDto(result.id(), state));
			default:
				throw new Exception(String.format("function was created with unexpected state: %s", state));
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/by-project/{projectId}")
	public RestResponse<List<FsclEntityData>> getAllFunctions(@PathParam("projectId") String projectId)
			throws Exception {
		List<FsclEntityData> functions = this.lifeCycleService.getAllForProject(projectId);

		return RestResponse.ok(functions);
	}
}
