package org.iteabag.simulator.util;

public class Time {

    private final long milliseconds;

    public Time(int milliseconds) {
        super();
        this.milliseconds = milliseconds;
    }

    public Time(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public long getInMilliseconds() {
        return milliseconds;
    }

    @Override
    public String toString() {
        return milliseconds + "ms";
    }
}
