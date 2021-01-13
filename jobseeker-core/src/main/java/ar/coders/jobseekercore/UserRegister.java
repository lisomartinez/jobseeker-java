package ar.coders.jobseekercore;

public class UserRegister {
    private final UserRepository userRepository;

    public UserRegister(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserId id, UserFirstName firstName, UserLastName lastName, UserEmail email) {
        assertIsNotRegistered(id);
        User newUser = User.from(id, firstName, lastName, email);
        userRepository.save(newUser);
    }

    private void assertIsNotRegistered(UserId id) {
        if (isRegistered(id)) throw new DomainException("User already register");
    }

    public boolean isRegistered(UserId id) {
        return userRepository.findById(id).isPresent();
    }
}
