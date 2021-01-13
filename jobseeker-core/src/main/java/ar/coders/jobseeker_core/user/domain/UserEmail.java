package ar.coders.jobseeker_core.user.domain;

import ar.coders.jobseeker_core.ValueObject;

public class UserEmail extends ValueObject<String> {
    public UserEmail(String value) {
        super(value);
    }


    public static UserEmail of(String email) {
        return new UserEmail(email);
    }
}
