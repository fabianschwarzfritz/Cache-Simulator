package org.iteabag.simulator.cache.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.iteabag.simulator.cache.Storeable;
import org.iteabag.simulator.cache.key.Key;
import org.iteabag.simulator.cache.value.Value;
import org.iteabag.simulator.device.impl.StoreageFullException;
import org.iteabag.simulator.util.Pair;

public class LeastRecentlyUsedCache extends Cache {

    private List<Pair<Date, Key>> dates;

    public LeastRecentlyUsedCache(final Storeable<Key, Value> cacheStorage,
            final Storeable<Key, Value> backgroundStorage, final String name) {
        super(cacheStorage, backgroundStorage, name);
        dates = new ArrayList<>();
    }

    @Override
    public Value get(final Key key) {
        Value value = cacheStorage.get(key);
        if (value == null) {
            miss.addAndGet(1);
            return backgroundStorage.get(key);
        }
        hit.addAndGet(1);
        return value;
    }

    @Override
    public void remove(final Key key) {
        cacheStorage.remove(key);
        backgroundStorage.remove(key);
    }

    @Override
    public Key put(final Key key, final Value value) {
        backgroundStorage.put(key, value);
        try {
            cacheStorage.put(key, value);
        } catch (StoreageFullException exeption) {
            supress();
            cacheStorage.put(key, value);
        }
        return key;
    }

    private synchronized void supress() {
        Collections.sort(dates, new PairComparator<Date, Key>());
        Pair<Date, Key> oldest = dates.get(0);
        Key oldestKey = oldest.getB();
        cacheStorage.remove(oldestKey);
    }

    @Override
    public int size() {
        return cacheStorage.size();
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
        return "LRUCache " + name;
    }

    @Override
    public String toString() {
        return getName() + " hits: " + hit + " miss: " + miss + " set: " + keySet();
    }
}
