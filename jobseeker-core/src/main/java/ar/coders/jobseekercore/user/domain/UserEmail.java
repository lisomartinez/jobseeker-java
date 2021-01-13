package ar.coders.jobseekercore.user.domain;

import ar.coders.jobseekercore.ValueObject;

public class UserEmail extends ValueObject<String> {
    public UserEmail(String value) {
        super(value);
    }

    public static UserEmail of(String email) {
        return new UserEmail(email);
    }
}
