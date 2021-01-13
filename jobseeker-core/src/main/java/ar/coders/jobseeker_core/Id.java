package ar.coders.jobseeker_core;

public class Id extends ValueObject<String> {

    public Id(String value) {
        super(value);
    }

    public String asString() {
        return value;
    }

}
