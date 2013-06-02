package org.iteabag.simulator.device.impl;

import org.iteabag.simulator.cache.key.Key;
import org.iteabag.simulator.cache.value.Value;

public class FiniteStorage<K, V> extends StorageImpl<K, V> {

    private final int maxCapacity;

    public FiniteStorage(final int maxCapacity, final String name) {
        super(name);
        this.maxCapacity = maxCapacity;
    }

    @Override
    public Key put(final Key key, final Value value) {
        if (storage.size() >= maxCapacity) {
            throw new StoreageFullException();
        }
        return super.put(key, value);
    }
}
