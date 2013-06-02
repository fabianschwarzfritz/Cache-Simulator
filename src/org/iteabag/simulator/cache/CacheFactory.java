package org.iteabag.simulator.cache;

import org.iteabag.simulator.cache.impl.Cache;
import org.iteabag.simulator.cache.impl.NoCache;
import org.iteabag.simulator.cache.impl.RandomCache;
import org.iteabag.simulator.cache.key.Key;
import org.iteabag.simulator.cache.value.Value;

public class CacheFactory {
    public static Cache randomCache(final Storeable<Key, Value> cache, final Storeable<Key, Value> background,
            final String name) {
        return new RandomCache(cache, background, name);
    }

    public static Cache noCache(final Storeable<Key, Value> background, final String name) {
        return new NoCache(background, name);
    }
}
