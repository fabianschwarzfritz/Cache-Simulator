package org.iteabag.simulator.cache.impl;

import java.util.Set;

import org.iteabag.simulator.cache.Storeable;
import org.iteabag.simulator.cache.key.Key;
import org.iteabag.simulator.cache.value.Value;

public class NoCache extends Cache {
    private String name;

    public NoCache(final Storeable<Key, Value> backgroundStorage, final String name) {
        super(null, backgroundStorage);
        this.name = name;
    }

    @Override
    public synchronized Value get(Key key) {
        System.out.println(getName() + " Get " + key);
        return backgroundStorage.get(key);
    }

    @Override
    public synchronized void remove(Key key) {
        System.out.println(getName() + " Remove" + key);
        backgroundStorage.remove(key);
    }

    @Override
    public synchronized Key put(Key key, Value value) {
        backgroundStorage.put(key, value);
        return key;
    }

    @Override
    public int size() {
        return cacheStorage.size();
    }

    @Override
    public Set<Key> keySet() {
        return backgroundStorage.keySet();
    }

    @Override
    public String getName() {
        return "Nocahce " + name;
    }

    @Override
    public String toString() {
        return getName() + " set: " + keySet();
    }
}
