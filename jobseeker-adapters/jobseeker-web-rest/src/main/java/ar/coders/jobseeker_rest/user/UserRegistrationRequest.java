package ar.coders.jobseeker_rest.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationRequest {

    @UUID
    public final String id;

    @Size(min = 3, max = 50)
    public final String firstName;

    @Size(min = 3, max = 50)
    public final String lastName;

    @Email
    public final String email;
}
