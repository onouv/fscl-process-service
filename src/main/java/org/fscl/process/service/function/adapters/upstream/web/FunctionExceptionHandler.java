package org.fscl.process.service.function.adapters.upstream.web;



import io.quarkus.logging.Log;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.fscl.core.ports.upstream.web.error.WebError;
import org.fscl.process.service.function.adapters.downstream.persistence.PersistenceException;


@Provider
public class FunctionExceptionHandler implements ExceptionMapper<Throwable> {

    @ConfigProperty(name = "function.api.error.unknown")
    String errorCode;

    @Override
    public Response toResponse(Throwable e) {
        /*Log.error(String.format("Could not create function %s, code: %s }",
                e.id.project(),
                e.id.code()));

         */

        Log.error("Unknown error handling a Function");
        WebError err = new WebError();
        err.setErrorCode(errorCode);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).build();
    }
}
