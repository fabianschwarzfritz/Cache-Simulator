package org.iteabag.simulator.cache.value;

public class StringValue implements Value {

    private String string;

    public StringValue(final String string) {
        this.string = string;
    }

    @Override
    public int hashCode() {
        return string.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return string.equals((String) obj);
    }

    @Override
    public String toString() {
        return string;
    }
}
