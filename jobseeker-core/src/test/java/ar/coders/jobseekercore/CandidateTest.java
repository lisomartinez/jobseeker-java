package ar.coders.jobseekercore;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CandidateTest {
    @Test
    void whenCreatedHaveNotAppliedToJobs() {
        Candidate candidate = new Candidate();

        assertThat(candidate.hasAppliedToJobs()).isFalse();
        assertThat(candidate.numberOfApplications()).isZero();
    }

    @Test
    void canApplyToJobs() {
        Candidate candidate = new Candidate();
        JobApplication javaAtAccenture = new JobApplication("Java", "Accenture", "Description");

        candidate.applyToJob(javaAtAccenture);

        assertThat(candidate.hasAppliedToJobs()).isTrue();
        assertThat(candidate.numberOfApplications()).isEqualTo(1);
    }

    @Test
    void knowsJobsApplied() {
        Candidate candidate = new Candidate();
        JobApplication javaAtAccenture = new JobApplication("Java", "Accenture", "Description");

        candidate.applyToJob(javaAtAccenture);

        assertThat(candidate.hasAppliedTo(javaAtAccenture)).isTrue();
        assertThat(candidate.numberOfApplications()).isEqualTo(1);
    }

    @Test
    void canApplyToMultipleJobs() {
        Candidate candidate = new Candidate();
        JobApplication netAtAccenture = new JobApplication(".NET", "Accenture", "Description");
        JobApplication javaAtAccenture = new JobApplication("Java", "Accenture", "Description");

        candidate.applyToJob(netAtAccenture);
        candidate.applyToJob(javaAtAccenture);

        assertThat(candidate.hasAppliedTo(netAtAccenture)).isTrue();
        assertThat(candidate.hasAppliedTo(javaAtAccenture)).isTrue();
        assertThat(candidate.numberOfApplications()).isEqualTo(2);
    }

    @Test
    void cannotApplyToSameJobMoreThanOnce() {
        Candidate candidate = new Candidate();
        JobApplication javaAtAccenture = new JobApplication("Java", "Accenture", "Description");

        candidate.applyToJob(javaAtAccenture);
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> candidate.applyToJob(javaAtAccenture))
                .withMessage("Cannot apply to same job more than once");

        assertThat(candidate.hasAppliedTo(javaAtAccenture)).isTrue();
        assertThat(candidate.numberOfApplications()).isEqualTo(1);
    }

    @Test
    void canAddCommentsOnAppliedJobs() {
        Candidate candidate = new Candidate();
        JobApplication javaAtAccenture = new JobApplication("Java", "Accenture", "Description");
        candidate.applyToJob(javaAtAccenture);
        OffsetDateTime dateTime = OffsetDateTime.now();
        Comment comment = new Comment("comment", dateTime);

        candidate.commentOnApplication(javaAtAccenture, comment);

        assertThat(candidate.hasCommentedOnApplication(javaAtAccenture, comment)).isTrue();
    }

    @Test
    void canAddMultipleCommentsOnAppliedJobs() {
        Candidate candidate = new Candidate();
        JobApplication javaAtAccenture = new JobApplication("Java", "Accenture", "Description");
        candidate.applyToJob(javaAtAccenture);
        OffsetDateTime dateTime = OffsetDateTime.now();
        Comment aComment = new Comment("comment", dateTime);
        Comment aSecondComment = new Comment("a second comment", dateTime);

        candidate.commentOnApplication(javaAtAccenture, aComment);
        candidate.commentOnApplication(javaAtAccenture, aSecondComment);

        assertThat(candidate.hasCommentedOnApplication(javaAtAccenture, aComment)).isTrue();
        assertThat(candidate.hasCommentedOnApplication(javaAtAccenture, aSecondComment)).isTrue();
    }

    @Test
    void cannotCommentOnNotAppliedJob() {
        Candidate candidate = new Candidate();
        JobApplication javaAtAccenture = new JobApplication("Java", "Accenture", "Description");
        Comment aComment = new Comment("comment", OffsetDateTime.now());
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> candidate.commentOnApplication(javaAtAccenture, aComment))
                .withMessage("Cannot comment on not applied jobs");
    }
}
