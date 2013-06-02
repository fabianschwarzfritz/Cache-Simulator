package org.iteabag.simulator.cache.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.iteabag.simulator.cache.Storeable;
import org.iteabag.simulator.cache.key.Key;
import org.iteabag.simulator.cache.value.Value;
import org.iteabag.simulator.device.impl.StoreageFullException;

public class RandomCache extends Cache {

    public RandomCache(final Storeable<Key, Value> cacheStorage, final Storeable<Key, Value> backgroundStorage,
            final String name) {
        super(cacheStorage, backgroundStorage, name);
    }

    @Override
    public synchronized Value get(Key key) {
        Value value = cacheStorage.get(key);
        if (value == null) {
            miss.addAndGet(1);
            return backgroundStorage.get(key);
        }
        hit.addAndGet(1);
        return value;
    }

    @Override
    public synchronized void remove(Key key) {
        cacheStorage.remove(key);
        backgroundStorage.remove(key);
    }

    @Override
    public synchronized Key put(Key key, Value value) {
        backgroundStorage.put(key, value);
        try {
            cacheStorage.put(key, value);
        } catch (StoreageFullException exeption) {
            supress();
            cacheStorage.put(key, value);
        }
        return key;
    }

    @Override
    public int size() {
        return cacheStorage.size();
    }

    private synchronized void supress() {
        int size = cacheStorage.size();
        Random random = new Random();
        int toRemove = random.nextInt(size);

        List<Key> keys = new ArrayList<Key>(cacheStorage.keySet());
        Key randomKey = keys.get(toRemove);
        cacheStorage.remove(randomKey);
    }

    @Override
    public Set<Key> keySet() {
        Set<Key> result = new HashSet<>();
        Set<Key> keysBackground = backgroundStorage.keySet();
        Set<Key> keysCache = cacheStorage.keySet();
        synchronized (result) {
            result.addAll(keysCache);
            result.addAll(keysBackground);
        }
        return result;
    }

    @Override
    public String getName() {
        return "RandomCache " + name;
    }

    @Override
    public String toString() {
        return getName() + " hits: " + hit + " miss: " + miss + " set: " + keySet();
    }
}
