package org.iteabag.simulator.cache.key;

public class StringKey implements Key {

    private String key;

    public StringKey(final String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object obj) {
        return key.equals(obj);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return key;
    }
}
