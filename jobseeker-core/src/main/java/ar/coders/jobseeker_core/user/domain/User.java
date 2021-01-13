package ar.coders.jobseeker_core.user.domain;

public class User {
    private final UserId id;
    private final UserFirstName firstName;
    private final UserLastName lastName;
    private final UserEmail email;


    public User(UserId id, UserFirstName firstName, UserLastName lastName, UserEmail email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public static User from(UserId id, UserFirstName firstName, UserLastName lastName, UserEmail email) {
        return new User(id, firstName, lastName, email);
    }

    public boolean isIdentifiedAs(UserId userId) {
        return id.equals(userId);
    }

    public UserId id() {
        return id;
    }

    public String idAsString() {
        return id.asString();
    }

    public String firstNameAsString() {
        return firstName.asString();
    }

    public String lastNameAsString() {
        return lastName.asString();
    }

    public String emailAsString() {
        return email.asString();
    }
}
