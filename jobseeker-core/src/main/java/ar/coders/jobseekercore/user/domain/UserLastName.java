package ar.coders.jobseekercore.user.domain;

import ar.coders.jobseekercore.ValueObject;

public class UserLastName extends ValueObject<String> {
    public UserLastName(String value) {
        super(value);
    }

    public static UserLastName of(String lastName) {
        return new UserLastName(lastName);
    }
}
