package org.iteabag.simulator.util;

import java.util.Stack;

public class Util {
    public static <T> Stack<T> deepCopyStack(Stack<T> toCopy) {
        Stack<T> result = new Stack<>();
        for (T t : toCopy) {
            result.push(t);
        }
        return result;
    }
}
