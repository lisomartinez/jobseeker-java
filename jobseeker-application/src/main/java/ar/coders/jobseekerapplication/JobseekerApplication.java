package ar.coders.jobseekerapplication;

import ar.coders.jobseeker_core.user.application.ApplicationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {"ar.coders"}
)
@ComponentScan(
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ApplicationService.class),
        value = {"ar.coders"}
)
@EntityScan(basePackages = "ar.coders.jobseeker_persistence_jpa")
@EnableJpaRepositories(basePackages = {"ar.coders.jobseeker_persistence_jpa"})
public class JobseekerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobseekerApplication.class, args);
    }

}
