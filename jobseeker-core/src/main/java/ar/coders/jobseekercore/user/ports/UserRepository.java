package ar.coders.jobseekercore.user.ports;

import ar.coders.jobseekercore.user.domain.User;
import ar.coders.jobseekercore.user.domain.UserId;

import java.util.Optional;

public interface UserRepository {
    void save(User newUser);

    Optional<User> findById(UserId id);
}
