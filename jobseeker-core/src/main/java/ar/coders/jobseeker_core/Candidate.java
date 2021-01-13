package ar.coders.jobseeker_core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Candidate {
    private final Map<JobApplication, List<Comment>> applications;

    public Candidate() {
        applications = new HashMap<>();
    }

    public boolean hasAppliedToJobs() {
        return !applications.isEmpty();
    }

    public boolean hasAppliedTo(JobApplication jobApplication) {
        return applications.containsKey(jobApplication);
    }

    public int numberOfApplications() {
        return applications.size();
    }

    public void applyToJob(JobApplication jobApplication) {
        assertThatNotAlreadyApplied(jobApplication);
        this.applications.putIfAbsent(jobApplication, new ArrayList<>());
    }

    private void assertThatNotAlreadyApplied(JobApplication jobApplication) {
        if (applications.containsKey(jobApplication))
            throw new DomainException("Cannot apply to same job more than once");
    }

    public void commentOnApplication(JobApplication javaAtAccenture, Comment aComment) {
        assertHasAppliedToJobs(javaAtAccenture);
        applications.get(javaAtAccenture).add(aComment);
    }

    private void assertHasAppliedToJobs(JobApplication javaAtAccenture) {
        if (!applications.containsKey(javaAtAccenture)) throw new DomainException("Cannot comment on not applied jobs");
    }

    public boolean hasCommentedOnApplication(JobApplication javaAtAccenture, Comment comment) {
        return applications.get(javaAtAccenture).contains(comment);
    }
}
