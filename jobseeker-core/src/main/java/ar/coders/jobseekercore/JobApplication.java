package ar.coders.jobseekercore;

import java.util.Objects;

public class JobApplication {
    private final String position;
    private final String company;
    private final String description;

    public JobApplication(String position, String company, String description) {
        this.position = position;
        this.company = company;
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobApplication)) return false;
        JobApplication that = (JobApplication) o;
        return getPosition().equals(that.getPosition()) && getPosition().equals(that.getPosition()) && getDescription().equals(
                that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getPosition(), getDescription());
    }
}
