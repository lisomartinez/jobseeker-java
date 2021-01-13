package ar.coders.jobseeker_persistence_jpa.users;

import ar.coders.jobseeker_core.user.domain.User;
import ar.coders.jobseeker_core.user.domain.UserAlreadyRegisteredException;
import ar.coders.jobseeker_core.user.domain.UserId;
import ar.coders.jobseeker_core.user.ports.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepository {

    private JpaUserRepository jpaUserRepository;

    public UserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public void save(User newUser) {
        try {
            UserEntity user = UserEntity.from(newUser);
            jpaUserRepository.save(user);
        } catch (ConstraintViolationException e) {
            throw new UserAlreadyRegisteredException();
        }

    }

    @Override
    public Optional<User> findById(UserId id) {
        Optional<UserEntity> userEntity = jpaUserRepository.findByUserId(id.asString());
        return userEntity.map(UserEntity::toUser);
    }
}
