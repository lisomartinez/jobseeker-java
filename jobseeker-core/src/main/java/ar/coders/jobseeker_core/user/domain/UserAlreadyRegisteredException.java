package ar.coders.jobseeker_core.user.domain;

import ar.coders.jobseeker_core.DomainException;

public class UserAlreadyRegisteredException extends DomainException {

    public UserAlreadyRegisteredException() {
        super("User already registered");
    }
}
