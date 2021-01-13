package ar.coders.jobseekercore;

import java.util.UUID;

public class User {
    private String identifier;

    public User(String identifier) {
        this.identifier = identifier;
    }

    public static User identifiedAs(String identifier) {
        assertIdentifierHasUUIDFormat(identifier);
        return new User(identifier);
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

    public boolean isIdentifiedAs(String userId) {
        return identifier.equals(userId);
    }
}
