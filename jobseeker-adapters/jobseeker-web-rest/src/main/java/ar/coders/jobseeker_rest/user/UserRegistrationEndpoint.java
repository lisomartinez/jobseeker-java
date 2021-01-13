package ar.coders.jobseeker_rest.user;

import ar.coders.jobseeker_core.user.application.CreateUserCommand;
import ar.coders.jobseeker_core.user.application.UserCreationPort;
import ar.coders.jobseeker_core.user.domain.UserEmail;
import ar.coders.jobseeker_core.user.domain.UserFirstName;
import ar.coders.jobseeker_core.user.domain.UserId;
import ar.coders.jobseeker_core.user.domain.UserLastName;
import ar.coders.jobseeker_rest.exceptions.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class UserRegistrationEndpoint {
    private final UserCreationPort userCreator;

    public UserRegistrationEndpoint(UserCreationPort userCreator) {
        this.userCreator = userCreator;
    }

    @Operation(summary = "Register a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "User already registered",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class),
                                    examples = @ExampleObject(value = "{\n" +
                                            "    \"statusCode\": 400,\n" +
                                            "    \"route\": \"http://localhost:8080/users\",\n" +
                                            "    \"method\": \"POST\",\n" +
                                            "    \"message\": \"User already registered\"\n" +
                                            "}"))
                    })
    })
    @PostMapping("/users")
    public ResponseEntity<Void> register(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        UserId id = UserId.of(userRegistrationRequest.getId());
        UserFirstName firstName = UserFirstName.of(userRegistrationRequest.getFirstName());
        UserLastName lastName = UserLastName.of(userRegistrationRequest.getLastName());
        UserEmail email = UserEmail.of(userRegistrationRequest.getEmail());

        userCreator.register(new CreateUserCommand(id, firstName, lastName, email));

        return ResponseEntity
                .created(URI.create("/users" + id.asString()))
                .build();
    }
}
