package org.iteabag.simulator.device.impl;

public class StoreageFullException extends RuntimeException {

    public StoreageFullException() {
        super("Storeage is full!");
    }

}
