package ar.coders.jobseeker_core.user.application;

import ar.coders.jobseeker_core.user.domain.UserEmail;
import ar.coders.jobseeker_core.user.domain.UserFirstName;
import ar.coders.jobseeker_core.user.domain.UserId;
import ar.coders.jobseeker_core.user.domain.UserLastName;

public class CreateUserCommand {
    private final UserId id;
    private final UserFirstName firstName;
    private final UserLastName lastName;
    private final UserEmail email;

    public CreateUserCommand(UserId id, UserFirstName firstName, UserLastName lastName, UserEmail email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserId getId() {
        return id;
    }

    public UserFirstName getFirstName() {
        return firstName;
    }

    public UserLastName getLastName() {
        return lastName;
    }

    public UserEmail getEmail() {
        return email;
    }
}
