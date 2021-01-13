package ar.coders.jobseeker_core;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CandidateTest {

    private TestObjectFactory objectFactory = new TestObjectFactory();

    @Test
    void whenCreatedHaveNotAppliedToJobs() {
        Candidate candidate = objectFactory.createCandidate();

        assertThat(candidate.hasAppliedToJobs()).isFalse();
        assertThat(candidate.numberOfApplications()).isZero();
    }

    @Test
    void canApplyToJobs() {
        Candidate candidate = objectFactory.createCandidate();
        JobApplication javaAtAccenture = objectFactory.createJavaAtAccentureApplication();

        candidate.applyToJob(javaAtAccenture);

        assertThat(candidate.hasAppliedToJobs()).isTrue();
        assertThat(candidate.numberOfApplications()).isEqualTo(1);
    }

    @Test
    void knowsJobsApplied() {
        Candidate candidate = objectFactory.createCandidate();
        JobApplication javaAtAccenture = objectFactory.createJavaAtAccentureApplication();

        candidate.applyToJob(javaAtAccenture);

        assertThat(candidate.hasAppliedTo(javaAtAccenture)).isTrue();
        assertThat(candidate.numberOfApplications()).isEqualTo(1);
    }

    @Test
    void canApplyToMultipleJobs() {
        Candidate candidate = objectFactory.createCandidate();
        JobApplication netAtAccenture = objectFactory.createNetAtAccentureApplication();
        JobApplication javaAtAccenture = objectFactory.createJavaAtAccentureApplication();

        candidate.applyToJob(netAtAccenture);
        candidate.applyToJob(javaAtAccenture);

        assertThat(candidate.hasAppliedTo(netAtAccenture)).isTrue();
        assertThat(candidate.hasAppliedTo(javaAtAccenture)).isTrue();
        assertThat(candidate.numberOfApplications()).isEqualTo(2);
    }

    @Test
    void cannotApplyToSameJobMoreThanOnce() {
        Candidate candidate = objectFactory.createCandidate();
        JobApplication javaAtAccenture = objectFactory.createJavaAtAccentureApplication();

        candidate.applyToJob(javaAtAccenture);
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> candidate.applyToJob(javaAtAccenture))
                .withMessage("Cannot apply to same job more than once");

        assertThat(candidate.hasAppliedTo(javaAtAccenture)).isTrue();
        assertThat(candidate.numberOfApplications()).isEqualTo(1);
    }

    @Test
    void canAddCommentsOnAppliedJobs() {
        Candidate candidate = objectFactory.createCandidate();
        JobApplication javaAtAccenture = objectFactory.createJavaAtAccentureApplication();
        candidate.applyToJob(javaAtAccenture);
        OffsetDateTime dateTime = OffsetDateTime.now();
        Comment comment = new Comment("comment", dateTime);

        candidate.commentOnApplication(javaAtAccenture, comment);

        assertThat(candidate.hasCommentedOnApplication(javaAtAccenture, comment)).isTrue();
    }

    @Test
    void canAddMultipleCommentsOnAppliedJobs() {
        Candidate candidate = objectFactory.createCandidate();
        JobApplication javaAtAccenture = objectFactory.createJavaAtAccentureApplication();
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
        Candidate candidate = objectFactory.createCandidate();
        JobApplication javaAtAccenture = objectFactory.createJavaAtAccentureApplication();
        Comment aComment = new Comment("comment", OffsetDateTime.now());
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> candidate.commentOnApplication(javaAtAccenture, aComment))
                .withMessage("Cannot comment on not applied jobs");
    }
}
