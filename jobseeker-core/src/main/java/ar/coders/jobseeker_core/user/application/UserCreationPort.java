package ar.coders.jobseeker_core.user.application;

import ar.coders.jobseeker_core.user.domain.UserId;

public interface UserCreationPort {
    void register(CreateUserCommand createUserCommand);

    boolean isRegistered(UserId id);
}
