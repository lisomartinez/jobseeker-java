package ar.coders.jobseekercore;

public class UserFirstName extends ValueObject<String> {
    public UserFirstName(String value) {
        super(value);
    }

    public static UserFirstName of(String firstName) {
        return new UserFirstName(firstName);
    }
}
