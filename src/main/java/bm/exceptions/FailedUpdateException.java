package bm.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class FailedUpdateException extends RuntimeException implements ExceptionMapper<FailedUpdateException> {

    private static final String DEFAULT_ERROR = "Failed to update item";

    public FailedUpdateException() {
        super(DEFAULT_ERROR);
    }

    public FailedUpdateException(String message) {
        super(message);
        System.out.println(message);
    }

    @Override
    public Response toResponse(FailedUpdateException exception) {
        return Response.status(400).entity(exception.getMessage())
                .type("text/plain").build();
    }
}
