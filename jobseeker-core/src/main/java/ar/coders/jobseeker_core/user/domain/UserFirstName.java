package ar.coders.jobseeker_core.user.domain;

import ar.coders.jobseeker_core.ValueObject;

public class UserFirstName extends ValueObject<String> {
    public UserFirstName(String value) {
        super(value);
    }


    public static UserFirstName of(String firstName) {
        return new UserFirstName(firstName);
    }
}
