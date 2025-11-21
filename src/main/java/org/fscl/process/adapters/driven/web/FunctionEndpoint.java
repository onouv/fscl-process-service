package org.fscl.process.adapters.driven.web;

import java.util.List;

import org.fscl.core.adapters.driven.web.ResourceExistingResponseDto;
import org.fscl.core.application.ResourceRecord;
import org.fscl.core.commons.ResourceData;
import org.fscl.core.ports.lifecycle.ResourceDistributionState;
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
	public RestResponse<ResourceExistingResponseDto> createFunction(ResourceData dto) throws Exception {

		ResourceRecord result = lifeCycleService.createFunction(dto.getResourceId(), dto.getName(),
				dto.getDescription());
		ResourceDistributionState state = result.state();
		switch (state) {
			case CreatedInView:
				return RestResponse.ok(new ResourceExistingResponseDto(result.id(), state));
			case PreexistingInView:
				return RestResponse.status(RestResponse.Status.CONFLICT,
						new ResourceExistingResponseDto(result.id(), state));
			default:
				throw new Exception(String.format("function was created with unexpected state: %s", state));
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/by-project/{projectId}")
	public RestResponse<List<ResourceData>> getAllFunctions(@PathParam("projectId") String projectId) throws Exception {
		List<ResourceData> functions = this.lifeCycleService.getAllForProject(projectId);

		return RestResponse.ok(functions);
	}
}
