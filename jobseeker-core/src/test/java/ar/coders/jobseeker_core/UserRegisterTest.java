package ar.coders.jobseeker_core;

import ar.coders.jobseeker_core.user.application.CreateUserCommand;
import ar.coders.jobseeker_core.user.application.UserCreationPort;
import ar.coders.jobseeker_core.user.application.UserRegister;
import ar.coders.jobseeker_core.user.domain.*;
import ar.coders.jobseeker_core.user.ports.LocalUserRepository;
import ar.coders.jobseeker_core.user.ports.UserRepository;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class UserRegisterTest {
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new LocalUserRepository();
    }

    @Test
    void canRegisterUser() {
        UserCreationPort userRegister = new UserRegister(userRepository);
        UserId id = UserId.of(UUID.randomUUID().toString());
        userRegister.register(
                new CreateUserCommand(id,
                                      UserFirstName.of("Lisandro"),
                                      UserLastName.of("Martinez"),
                                      UserEmail.of("lisandro@company.com")));
        assertThat(userRegister.isRegistered(id)).isTrue();
    }

    @Test
    void whenCreatedHasNoUsers() {
        UserCreationPort userRegister = new UserRegister(userRepository);
        UserId id = UserId.of(UUID.randomUUID().toString());
        assertThat(userRegister.isRegistered(id)).isFalse();
    }

    @Test
    void canRegisterMultipleUsers() {
        UserCreationPort userRegister = new UserRegister(userRepository);
        UserId id = UserId.of(UUID.randomUUID().toString());
        UserId otherId = UserId.of(UUID.randomUUID().toString());
        userRegister.register(
                new CreateUserCommand(id,
                                      UserFirstName.of("Lisandro"),
                                      UserLastName.of("Martinez"),
                                      UserEmail.of("lisandro@company.com")));

        userRegister.register(
                new CreateUserCommand(otherId,
                                      UserFirstName.of("Pedro"),
                                      UserLastName.of("Martinez"),
                                      UserEmail.of("pedro@company.com")));

        assertThat(userRegister.isRegistered(id)).isTrue();
        assertThat(userRegister.isRegistered(otherId)).isTrue();
    }

    @Test
    void cannotRegisterUserMoreThanOnce() {
        UserCreationPort userRegister = new UserRegister(userRepository);
        UserId id = UserId.of(UUID.randomUUID().toString());
        userRegister.register(
                new CreateUserCommand(id,
                                      UserFirstName.of("Lisandro"),
                                      UserLastName.of("Martinez"),
                                      UserEmail.of("lisandro@company.com")));

        assertThatExceptionOfType(UserAlreadyRegisteredException.class)
                .isThrownBy(registerDuplicatedUser(userRegister, id))
                .withMessage("User already registered");

        assertThat(userRegister.isRegistered(id)).isTrue();

    }

    private ThrowableAssert.ThrowingCallable registerDuplicatedUser(UserCreationPort userRegister, UserId id) {
        return () -> userRegister.register(
                new CreateUserCommand(id,
                                      UserFirstName.of("Lisandro"),
                                      UserLastName.of("Martinez"),
                                      UserEmail.of("lisandro@company.com")));
    }
}
