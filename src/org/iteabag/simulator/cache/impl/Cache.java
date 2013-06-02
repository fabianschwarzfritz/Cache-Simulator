package org.iteabag.simulator.cache.impl;

import org.iteabag.simulator.cache.Storeable;
import org.iteabag.simulator.cache.key.Key;
import org.iteabag.simulator.cache.value.Value;

public abstract class Cache implements Storeable<Key, Value> {

    protected final Storeable<Key, Value> cacheStorage;
    protected final Storeable<Key, Value> backgroundStorage;

    public Cache(final Storeable<Key, Value> cacheStorage, final Storeable<Key, Value> backgroundStorage) {
        this.cacheStorage = cacheStorage;
        this.backgroundStorage = backgroundStorage;
    }

}
