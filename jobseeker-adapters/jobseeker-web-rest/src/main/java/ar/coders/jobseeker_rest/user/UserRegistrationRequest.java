package ar.coders.jobseeker_rest.user;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    public final String id;
    public final String firstName;
    public final String lastName;
    public final String email;
}
