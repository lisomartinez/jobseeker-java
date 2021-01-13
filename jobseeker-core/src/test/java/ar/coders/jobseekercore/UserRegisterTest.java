package ar.coders.jobseekercore;

import ar.coders.jobseekercore.user.application.UserCreationPort;
import ar.coders.jobseekercore.user.application.UserRegister;
import ar.coders.jobseekercore.user.domain.UserEmail;
import ar.coders.jobseekercore.user.domain.UserFirstName;
import ar.coders.jobseekercore.user.domain.UserId;
import ar.coders.jobseekercore.user.domain.UserLastName;
import ar.coders.jobseekercore.user.ports.UserRepository;
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
        userRegister.register(id,
                              UserFirstName.of("Lisandro"),
                              UserLastName.of("Martinez"),
                              UserEmail.of("lisandro@company.com"));
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
        userRegister.register(id,
                              UserFirstName.of("Lisandro"),
                              UserLastName.of("Martinez"),
                              UserEmail.of("lisandro@company.com"));

        userRegister.register(otherId,
                              UserFirstName.of("Pedro"),
                              UserLastName.of("Martinez"),
                              UserEmail.of("pedro@company.com"));

        assertThat(userRegister.isRegistered(id)).isTrue();
        assertThat(userRegister.isRegistered(otherId)).isTrue();
    }

    @Test
    void cannotRegisterUserMoreThanOnce() {
        UserCreationPort userRegister = new UserRegister(userRepository);
        UserId id = UserId.of(UUID.randomUUID().toString());
        userRegister.register(id,
                              UserFirstName.of("Lisandro"),
                              UserLastName.of("Martinez"),
                              UserEmail.of("lisandro@company.com"));

        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(registerDuplicatedUser(userRegister, id))
                .withMessage("User already register");

        assertThat(userRegister.isRegistered(id)).isTrue();

    }

    private ThrowableAssert.ThrowingCallable registerDuplicatedUser(UserCreationPort userRegister, UserId id) {
        return () -> userRegister.register(id,
                                           UserFirstName.of("Lisandro"),
                                           UserLastName.of("Martinez"),
                                           UserEmail.of("lisandro@company.com"));
    }
}
