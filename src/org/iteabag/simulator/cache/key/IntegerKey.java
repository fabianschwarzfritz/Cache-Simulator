package org.iteabag.simulator.cache.key;

public class IntegerKey implements Key {

    private int value;

    public IntegerKey(int key) {
        this.value = key;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + value;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IntegerKey other = (IntegerKey) obj;
        if (value != other.value)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Key " + value;
    }
}
