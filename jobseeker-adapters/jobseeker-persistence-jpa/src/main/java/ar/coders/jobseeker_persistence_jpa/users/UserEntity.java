package ar.coders.jobseeker_persistence_jpa.users;

import ar.coders.jobseeker_core.user.domain.*;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String userId;

    @NotNull
    @Column(length = 50)
    private String firstName;

    @NotNull
    @Column(length = 50)
    private String lastName;

    @NotNull
    private String email;

    public UserEntity(String userId, String firstName, String lastName, String email) {
        this(null, userId, firstName, lastName, email);
    }

    public static UserEntity from(User user) {
        return new UserEntity(user.idAsString(),
                              user.firstNameAsString(),
                              user.lastNameAsString(),
                              user.emailAsString());
    }

    public User toUser() {
        return User.from(UserId.of(userId),
                         UserFirstName.of(firstName),
                         UserLastName.of(lastName),
                         UserEmail.of(email));
    }
}
