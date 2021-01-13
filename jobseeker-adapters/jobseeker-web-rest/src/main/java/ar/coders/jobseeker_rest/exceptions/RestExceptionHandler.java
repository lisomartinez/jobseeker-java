package ar.coders.jobseeker_rest.exceptions;

import ar.coders.jobseeker_core.user.domain.UserAlreadyRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({UserAlreadyRegisteredException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleBadRequest(HttpServletRequest request, Exception ex) {
        return ErrorMessage.builder()
                           .statusCode(400)
                           .route(request.getRequestURL().toString())
                           .method(request.getMethod())
                           .message(ex.getMessage())
                           .build();
    }
}
