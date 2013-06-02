package org.iteabag.simulator.cache;

import org.iteabag.simulator.cache.key.Key;
import org.iteabag.simulator.cache.value.Value;

public interface KeyGenerator {
    Key generate(Value value);
}
