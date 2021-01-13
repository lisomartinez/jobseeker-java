package ar.coders.jobseeker_core.user.application;

import ar.coders.jobseeker_core.user.domain.User;
import ar.coders.jobseeker_core.user.domain.UserAlreadyRegisteredException;
import ar.coders.jobseeker_core.user.domain.UserId;
import ar.coders.jobseeker_core.user.ports.UserRepository;

@ApplicationService
public class UserRegister implements UserCreationPort {
    private final UserRepository userRepository;

    public UserRegister(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(CreateUserCommand createUserCommand) {
        assertIsNotRegistered(createUserCommand.getId());
        User newUser = User.from(createUserCommand.getId(),
                                 createUserCommand.getFirstName(),
                                 createUserCommand.getLastName(),
                                 createUserCommand.getEmail());
        userRepository.save(newUser);
    }

    private void assertIsNotRegistered(UserId id) {
        if (isRegistered(id)) throw new UserAlreadyRegisteredException();
    }

    @Override
    public boolean isRegistered(UserId id) {
        return userRepository.findById(id).isPresent();
    }
}
