package org.iteabag.simulator.util;

public class Pair<A, B> {
    private A a;
    private B b;

    public Pair(A a, B b) {
        super();
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    @Override
    public String toString() {
        return "Pair [a=" + a + ", b=" + b + "]";
    }
}
