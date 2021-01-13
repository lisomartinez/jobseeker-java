package ar.coders.jobseeker_core;

import ar.coders.jobseeker_core.user.domain.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class UserTest {

    private TestObjectFactory objectFactory = new TestObjectFactory();

    @Test
    void knowsItsIdentifier() {
        UserId userId = UserId.of(UUID.randomUUID().toString());
        User user = objectFactory.createLisandroMartinezUser(userId);

        assertThat(user.isIdentifiedAs(userId)).isTrue();
    }

    private User createUser(UserId userId) {
        return User.from(userId,
                         UserFirstName.of("Lisandro"),
                         UserLastName.of("Martinez"),
                         UserEmail.of("lisandro@company.com"));
    }

    @Test
    void knowIfItsNotHerIdentifier() {
        UserId userId = UserId.of(UUID.randomUUID().toString());
        UserId otherId = UserId.of(UUID.randomUUID().toString());

        User user = objectFactory.createLisandroMartinezUser(userId);
        assertThat(user.isIdentifiedAs(otherId)).isFalse();
    }

    @Test
    void userIdentifierHasUUIDFormat() {
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> UserId.of("other format"))
                .withMessage("User identifier should have UUID format");
    }

    @Test
    void cannotBeCreatedWithBlankIdentifier() {
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> UserId.of("  "))
                .withMessage("User identifier cannot be blank");
    }

    @Test
    void cannotBeCreatedWithNullIdentifier() {
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> UserId.of(null))
                .withMessage("User identifier cannot be null");
    }
}
