package ar.coders.jobseeker_rest.user;

import ar.coders.jobseeker_core.user.application.CreateUserCommand;
import ar.coders.jobseeker_core.user.application.UserCreationPort;
import ar.coders.jobseeker_core.user.domain.UserEmail;
import ar.coders.jobseeker_core.user.domain.UserFirstName;
import ar.coders.jobseeker_core.user.domain.UserId;
import ar.coders.jobseeker_core.user.domain.UserLastName;
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
