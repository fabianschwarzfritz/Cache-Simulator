package org.iteabag.simulator.device.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.iteabag.simulator.cache.Storeable;
import org.iteabag.simulator.cache.key.Key;
import org.iteabag.simulator.cache.value.Value;

public class StorageImpl<K, V> implements Storeable<Key, Value> {

    private String name;
    protected ConcurrentHashMap<Key, Value> storage;

    public StorageImpl(final String name) {
        this.name = name;
        storage = new ConcurrentHashMap<>();
    }

    public Value get(Key key) {
        return storage.get(key);
    }

    public int size() {
        return storage.size();
    }

    @Override
    public void remove(Key integer) {
        storage.remove(integer);
    }

    @Override
    public Key put(Key key, Value value) {
        storage.put(key, value);
        return key;
    }

    @Override
    public Set<Key> keySet() {
        Set<Key> result = new HashSet<>();
        Set<Key> keySet = storage.keySet();
        synchronized (keySet) {
            Iterator<Key> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                Key key = iterator.next();
                result.add(key);
            }
        }
        return result;
    }

    @Override
    public String getName() {
        return name;
    }
}
