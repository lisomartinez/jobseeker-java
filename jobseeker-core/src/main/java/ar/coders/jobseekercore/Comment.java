package ar.coders.jobseekercore;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Comment {
    private final String comment;
    private final OffsetDateTime dateTime;
}
