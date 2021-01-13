package ar.coders.jobseekercore;

import ar.coders.jobseekercore.user.domain.User;
import ar.coders.jobseekercore.user.domain.UserId;
import ar.coders.jobseekercore.user.ports.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LocalUserRepository implements UserRepository {
    private final Map<UserId, User> users;

    public LocalUserRepository() {
        users = new HashMap<>();
    }

    @Override
    public void save(User newUser) {
        users.put(newUser.id(), newUser);
    }

    @Override
    public Optional<User> findById(UserId id) {
        if (users.containsKey(id)) return Optional.of(users.get(id));
        return Optional.empty();
    }
}
