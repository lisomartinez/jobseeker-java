package ar.coders.jobseekercore;

import java.util.Optional;

public interface UserRepository {
    void save(User newUser);

    Optional<User> findById(UserId id);
}
