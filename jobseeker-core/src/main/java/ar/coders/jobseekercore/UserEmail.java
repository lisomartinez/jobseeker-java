package ar.coders.jobseekercore;

public class UserEmail extends ValueObject<String> {
    public UserEmail(String value) {
        super(value);
    }

    public static UserEmail of(String email) {
        return new UserEmail(email);
    }
}
