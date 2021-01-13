package ar.coders.jobseekercore.user.application;

import ar.coders.jobseekercore.DomainException;
import ar.coders.jobseekercore.user.domain.*;
import ar.coders.jobseekercore.user.ports.UserRepository;

public class UserRegister implements UserCreationPort {
    private final UserRepository userRepository;

    public UserRegister(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(UserId id, UserFirstName firstName, UserLastName lastName, UserEmail email) {
        assertIsNotRegistered(id);
        User newUser = User.from(id, firstName, lastName, email);
        userRepository.save(newUser);
    }

    private void assertIsNotRegistered(UserId id) {
        if (isRegistered(id)) throw new DomainException("User already register");
    }

    @Override
    public boolean isRegistered(UserId id) {
        return userRepository.findById(id).isPresent();
    }
}
