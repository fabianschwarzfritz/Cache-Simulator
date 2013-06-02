package org.iteabag.simulator.device.impl;

import java.util.Set;

import org.iteabag.simulator.cache.Storeable;
import org.iteabag.simulator.cache.key.Key;
import org.iteabag.simulator.cache.value.Value;
import org.iteabag.simulator.util.Time;
import org.iteabag.simulator.util.Waiter;

public class ConstantDevice<K, V> implements Storeable<Key, Value> {

    private final Time writetime;
    private final Time readtime;
    private final Storeable<Key, Value> storeage;

    public ConstantDevice(final Time writetime, final Time readtime, final String name) {
        this.writetime = writetime;
        this.readtime = readtime;
        storeage = new InfiniteStorage<K, V>(name);
    }

    @Override
    public Key put(Key key, Value value) {
        waitTime(writetime);
        return storeage.put(key, value);
    }

    public Value get(final Key key) {
        waitTime(readtime);
        return storeage.get(key);
    }

    @Override
    public void remove(Key integer) {
        waitTime(writetime);
        storeage.remove(integer);
    }

    @Override
    public int size() {
        return storeage.size();
    }

    @Override
    public Set<Key> keySet() {
        return storeage.keySet();
    }

    @Override
    public String getName() {
        return "ConstantDevice " + storeage.getName();
    }

    private void waitTime(final Time time) {
        Waiter w = new Waiter(time);
        w.waitTime();
    }
}
