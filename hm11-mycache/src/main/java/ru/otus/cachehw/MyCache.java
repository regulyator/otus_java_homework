package ru.otus.cachehw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author sergey
 * created on 14.12.18.
 */
public class MyCache<K, V> implements HwCache<K, V> {
    private static final Logger logger = LoggerFactory.getLogger(MyCache.class);
    private final Map<K, V> cache = new WeakHashMap<>(10);
    private final List<HwListener<K, V>> listeners = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
        notifyListeners(key, value, CacheAction.ADD);
    }

    @Override
    public void remove(K key) {
        V removedValue = cache.remove(key);
        notifyListeners(key, removedValue, CacheAction.REMOVE);
    }

    @Override
    public V get(K key) {
        return cache.get(key);

    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        if (Objects.nonNull(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        if (Objects.nonNull(listener)) {
            listeners.remove(listener);
        }
    }

    @Override
    public int size() {
        return cache.size();
    }

    private void notifyListeners(K key, V value, CacheAction cacheAction) {
        listeners
                .stream()
                .filter(Objects::nonNull)
                .forEach(kvHwListener -> {
                    try {
                        kvHwListener.notify(key, value, cacheAction.toString());
                    } catch (Exception ex) {
                        logger.error(ex.getMessage(), ex);
                    }

                });
    }
}
