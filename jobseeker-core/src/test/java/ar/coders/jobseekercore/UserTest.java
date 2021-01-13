package ar.coders.jobseekercore;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class UserTest {
    @Test
    void knowsItsIdentifier() {
        String userId = UUID.randomUUID().toString();
        User user = User.identifiedAs(userId);
        assertThat(user.isIdentifiedAs(userId)).isTrue();
    }

    @Test
    void knowIfItsNotHerIdentifier() {
        String userId = UUID.randomUUID().toString();
        User user = User.identifiedAs(userId);
        assertThat(user.isIdentifiedAs(UUID.randomUUID().toString())).isFalse();
    }

    @Test
    void userIdentifierHasUUIDFormat() {
        String userId = "other format";
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> User.identifiedAs(userId))
                .withMessage("User identifier should have UUID format");
    }

    @Test
    void cannotBeCreatedWithBlankIdentifier() {
        String userId = "  ";
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> User.identifiedAs(userId))
                .withMessage("User identifier cannot be blank");
    }

    @Test
    void cannotBeCreatedWithNullIdentifier() {
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> User.identifiedAs(null))
                .withMessage("User identifier cannot be null");
    }
}
