package ar.coders.jobseekercore;


import java.util.Objects;

public abstract class ValueObject<T> {
    private final T value;

    public ValueObject(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValueObject)) return false;
        ValueObject<?> that = (ValueObject<?>) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
