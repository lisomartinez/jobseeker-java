package ar.coders.jobseekercore;

public class UserLastName extends ValueObject<String> {
    public UserLastName(String value) {
        super(value);
    }

    public static UserLastName of(String lastName) {
        return new UserLastName(lastName);
    }
}
