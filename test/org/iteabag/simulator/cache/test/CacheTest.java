package org.iteabag.simulator.cache.test;

import java.util.Random;

import org.iteabag.simulator.cache.CacheFactory;
import org.iteabag.simulator.cache.Storeable;
import org.iteabag.simulator.cache.impl.Cache;
import org.iteabag.simulator.cache.impl.NoCache;
import org.iteabag.simulator.cache.key.IntegerKey;
import org.iteabag.simulator.cache.key.Key;
import org.iteabag.simulator.cache.key.StringKey;
import org.iteabag.simulator.cache.value.StringValue;
import org.iteabag.simulator.cache.value.Value;
import org.iteabag.simulator.device.impl.ConstantDevice;
import org.iteabag.simulator.device.impl.FiniteStorage;
import org.iteabag.simulator.util.Time;
import org.junit.Test;

public class CacheTest {

    private static final Integer CACHE_SIZE = new Integer(10);
    private static final Integer READ_TIMES = new Integer(100);
    private static final Integer WRITE_TIMES = new Integer(10);

    private static final Time WRITE_TIME_DISK = new Time(100);
    private static final Time READ_TIME_DISK = new Time(100);

    private static final Integer MAX_KEY = new Integer(100);

    private Cache randomCache;
    private Cache lruCache;
    private Cache noCache;

    public CacheTest() {
        Storeable<Key, Value> randomRAM = createFastStoreage(CACHE_SIZE, "faststorage1");
        Storeable<Key, Value> harddiskRandom = createHarddisk(WRITE_TIME_DISK, READ_TIME_DISK, "harddisk1");
        randomCache = CacheFactory.randomCache(randomRAM, harddiskRandom, "randomcache");

        Storeable<Key, Value> lruRAM = createFastStoreage(CACHE_SIZE, "faststorage2");
        Storeable<Key, Value> harddiskLRU = createHarddisk(WRITE_TIME_DISK, READ_TIME_DISK, "harddisk1");
        lruCache = CacheFactory.lruCache(lruRAM, harddiskLRU, "lrucache");

        Storeable<Key, Value> harddiskNoCache = createHarddisk(WRITE_TIME_DISK, READ_TIME_DISK, "harddisk2");
        noCache = new NoCache(harddiskNoCache, "nocache");
    }

    private Storeable<Key, Value> createHarddisk(Time writeTime, Time readTime, String name) {
        return new ConstantDevice<StringKey, StringValue>(writeTime, readTime, "platte");
    }

    private Storeable<Key, Value> createFastStoreage(int cachesize, String name) {
        return new FiniteStorage<StringKey, StringValue>(CACHE_SIZE, "cachedevice");
    }

    @Test
    public void test() {
        testCache(noCache);
        testCache(randomCache);
        testCache(lruCache);
    }

    public void testCache(final Cache cache) {
        long start = System.currentTimeMillis();
        doCacheTest(cache);
        long stop = System.currentTimeMillis();
        long t = stop - start;
        Time time = new Time(t);

        System.out.println("\n---");
        System.out.println("time\t" + time);
        System.out.println("Cache\t" + cache);
        System.out.println("Cache keys\t" + cache.keySet().size());
    }

    private void doCacheTest(final Cache cache) {
        for (int i = 0; i < WRITE_TIMES; i++) {
            Key generatedKey = generateKey();
            Value generatedValue = generateValue();
            cache.put(generatedKey, generatedValue);
        }

        for (int i = 0; i < READ_TIMES; i++) {
            Key generatedKey = generateKey();
            cache.get(generatedKey);
        }
    }

    private Key generateKey() {
        Random random = new Random();
        int nextInt = random.nextInt(MAX_KEY);
        return new IntegerKey(nextInt);
    }

    private Value generateValue() {
        Random random = new Random();
        int nextInt = random.nextInt(MAX_KEY);
        return new StringValue("value" + nextInt);
    }

}
