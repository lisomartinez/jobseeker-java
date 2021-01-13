package ar.coders.jobseeker_core.user.ports;

import ar.coders.jobseeker_core.user.domain.User;
import ar.coders.jobseeker_core.user.domain.UserId;

import java.util.Optional;

public interface UserRepository {
    void save(User newUser);

    Optional<User> findById(UserId id);
}
