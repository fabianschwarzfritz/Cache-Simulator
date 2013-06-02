package org.iteabag.simulator.util;

public class Waiter {

    private Time time;

    public Waiter(Time time) {
        this.time = time;
    }

    public void waitTime() {
        try {
            long milliseconds = time.getInMilliseconds();
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
