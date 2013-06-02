package org.iteabag.simulator.cache.impl;

import org.iteabag.simulator.cache.KeyGenerator;
import org.iteabag.simulator.cache.key.Key;
import org.iteabag.simulator.cache.key.StringKey;
import org.iteabag.simulator.cache.value.Value;

public class KeyGeneratorImpl implements KeyGenerator {

    @Override
    public Key generate(Value value) {
        Integer hash = value.hashCode();
        String hashString = hash.toString();
        return new StringKey(hashString);
    }

}
