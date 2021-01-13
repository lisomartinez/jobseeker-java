package ar.coders.jobseeker_rest.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
    private final int statusCode;
    private final String route;
    private final String method;
    private final String message;
}
