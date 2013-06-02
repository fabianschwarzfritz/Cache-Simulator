package org.iteabag.simulator.cache;

import java.util.Set;

import org.iteabag.simulator.cache.key.Key;
import org.iteabag.simulator.cache.value.Value;

public interface Storeable<K extends Key, V extends Value> {

    V get(K k);

    void remove(K integer);

    K put(K key, V value);

    int size();

    Set<K> keySet();

    String getName();
}
