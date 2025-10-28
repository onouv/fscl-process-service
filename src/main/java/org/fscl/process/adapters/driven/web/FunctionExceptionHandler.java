package org.fscl.process.adapters.driven.web;

import io.quarkus.logging.Log;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.fscl.core.ports.web.driven.error.ErrorDto;

@Provider
public class FunctionExceptionHandler implements ExceptionMapper<Throwable> {

    @ConfigProperty(name = "function.api.failure.unknown")
    private String errorCode;

    @Override
    public Response toResponse(Throwable e) {
        Log.error(String.format("Error handling a Function: %s", e.getMessage()));
        Log.error(String.format("... %s", e.getStackTrace()));
        Log.error(String.format("... %s", e.getCause()));
        
        
        ErrorDto err = new ErrorDto();
        err.setErrorCode(errorCode);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
    }
}
