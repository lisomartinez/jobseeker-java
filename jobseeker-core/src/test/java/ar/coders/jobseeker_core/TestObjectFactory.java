package ar.coders.jobseeker_core;

import ar.coders.jobseeker_core.user.domain.*;

public class TestObjectFactory {
    public JobApplication createJavaAtAccentureApplication() {
        return new JobApplication("Java", "Accenture", "Description");
    }

    public User createLisandroMartinezUser(UserId userId) {
        return User.from(userId,
                         UserFirstName.of("Lisandro"),
                         UserLastName.of("Martinez"),
                         UserEmail.of("lisandro@company.com"));
    }

    public Candidate createCandidate() {
        return new Candidate();
    }

    JobApplication createNetAtAccentureApplication() {
        return new JobApplication(".NET", "Accenture", "Description");
    }
}
