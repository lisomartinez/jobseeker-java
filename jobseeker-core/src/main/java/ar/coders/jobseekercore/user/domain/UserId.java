package ar.coders.jobseekercore.user.domain;

import ar.coders.jobseekercore.DomainException;
import ar.coders.jobseekercore.Id;

import java.util.UUID;

public class UserId extends Id {
    public UserId(String value) {
        super(value);
    }

    public static UserId of(String id) {
        assertIdentifierHasUUIDFormat(id);
        return new UserId(id);
    }

    private static void assertIdentifierHasUUIDFormat(String identifier) {
        if (identifier == null) throw new DomainException("User identifier cannot be null");
        if (identifier.isBlank()) throw new DomainException("User identifier cannot be blank");
        try {
            UUID.fromString(identifier);
        } catch (IllegalArgumentException e) {
            throw new DomainException("User identifier should have UUID format");
        }
    }
}
