package ar.coders.jobseeker_core.user.domain;

import ar.coders.jobseeker_core.DomainException;
import ar.coders.jobseeker_core.Id;

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
