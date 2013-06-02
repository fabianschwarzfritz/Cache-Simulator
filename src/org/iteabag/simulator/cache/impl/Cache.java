package org.iteabag.simulator.cache.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.iteabag.simulator.cache.Storeable;
import org.iteabag.simulator.cache.key.Key;
import org.iteabag.simulator.cache.value.Value;

public abstract class Cache implements Storeable<Key, Value> {

    protected String name;

    protected final AtomicInteger miss;
    protected final AtomicInteger hit;

    protected final Storeable<Key, Value> cacheStorage;
    protected final Storeable<Key, Value> backgroundStorage;

    public Cache(final Storeable<Key, Value> cacheStorage, final Storeable<Key, Value> backgroundStorage,
            final String name) {
        this.cacheStorage = cacheStorage;
        this.backgroundStorage = backgroundStorage;
        this.name = name;
        miss = new AtomicInteger(0);
        hit = new AtomicInteger(0);
    }

    public abstract String getName();

}
