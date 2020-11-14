package ru.otus.cachehw;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MyCacheTest {
    @Mock
    HwListener<Integer, String> hwListener;

    //параметры JVM -Xms128m -Xmx128m
    @Test
    void testWeakMap() throws InterruptedException {
        MyCache<Integer, String> myCache = new MyCache<>();


        for (int i = 0; i < 500; i++) {
            myCache.put(i, String.valueOf(i));
        }

        assertEquals(500, myCache.size());
        System.out.println(myCache.size());
        System.gc();
        Thread.sleep(1000);

        System.out.println(myCache.size());
        assertNotEquals(500, myCache.size());

    }


    @Test
    void testListeners() {
        MyCache<Integer, String> myCache = new MyCache<>();
        myCache.addListener(hwListener);
        myCache.addListener(new HwListener<Integer, String>() {
            @Override
            public void notify(Integer key, String value, String action) {
                throw new NullPointerException();
            }
        });

        for (int i = 0; i < 10; i++) {
            myCache.put(i, String.valueOf(i));
        }

        for (int i = 0; i < 10; i++) {
            myCache.remove(i);
        }

        verify(hwListener, times(20)).notify(any(), any(), any());
        verify(hwListener, times(10)).notify(any(), any(), eq(CacheAction.ADD.toString()));
        verify(hwListener, times(10)).notify(any(), any(), eq(CacheAction.REMOVE.toString()));

    }
}