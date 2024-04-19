package org.fscl.process.service.function.adapters.upstream.web;



import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.fscl.core.ports.upstream.web.error.WebError;


@Provider
public class FunctionExceptionHandler implements ExceptionMapper<Exception> {

    @ConfigProperty(name = "function.api.error.unknown")
    String errorCode;

    @Override
    public Response toResponse(Exception e) {
        WebError err = new WebError();
        err.setErrorCode(errorCode);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
    }
}
