package ar.coders.jobseekercore.user.domain;

import ar.coders.jobseekercore.ValueObject;

public class UserFirstName extends ValueObject<String> {
    public UserFirstName(String value) {
        super(value);
    }

    public static UserFirstName of(String firstName) {
        return new UserFirstName(firstName);
    }
}
