package ar.coders.jobseekerapplication;

import ar.coders.jobseeker_core.user.ports.UserRepository;
import ar.coders.jobseeker_persistence_jpa.users.JpaUserRepository;
import ar.coders.jobseeker_persistence_jpa.users.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public UserRepository userRepository(JpaUserRepository jpaUserRepository) {
        return new UserRepositoryAdapter(jpaUserRepository);
    }

}
