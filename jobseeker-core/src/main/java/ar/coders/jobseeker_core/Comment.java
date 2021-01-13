package ar.coders.jobseeker_core;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Comment {
    private final String comment;
    private final OffsetDateTime dateTime;
}
