package ar.coders.jobseekercore.user.application;

import ar.coders.jobseekercore.user.domain.UserEmail;
import ar.coders.jobseekercore.user.domain.UserFirstName;
import ar.coders.jobseekercore.user.domain.UserId;
import ar.coders.jobseekercore.user.domain.UserLastName;

public interface UserCreationPort {
    void register(UserId id, UserFirstName firstName, UserLastName lastName, UserEmail email);

    boolean isRegistered(UserId id);
}
