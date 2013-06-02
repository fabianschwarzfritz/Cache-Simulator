package org.iteabag.simulator.cache.impl;

import java.util.Set;

import org.iteabag.simulator.cache.Storeable;
import org.iteabag.simulator.cache.key.Key;
import org.iteabag.simulator.cache.value.Value;

public class LeastRecentlyUsedCache extends Cache {

    public LeastRecentlyUsedCache(final Storeable<Key, Value> cacheStorage,
            final Storeable<Key, Value> backgroundStorage, final String name) {
        super(cacheStorage, backgroundStorage, name);
    }

    @Override
    public Value get(final Key k) {
        return null;
    }

    @Override
    public void remove(final Key integer) {

    }

    @Override
    public Key put(final Key key, final Value value) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<Key> keySet() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

}
