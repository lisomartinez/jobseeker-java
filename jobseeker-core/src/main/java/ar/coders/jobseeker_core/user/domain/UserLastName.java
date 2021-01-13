package ar.coders.jobseeker_core.user.domain;

import ar.coders.jobseeker_core.ValueObject;

public class UserLastName extends ValueObject<String> {
    public UserLastName(String value) {
        super(value);
    }

    public static UserLastName of(String lastName) {
        return new UserLastName(lastName);
    }
}
