package ru.otus.homework;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GCResearchApp {

    public static void main(String... args) {

        final Map<String, Long> statistics = new HashMap<>();
        //запись времени старта
        final long start = System.currentTimeMillis();
        //инициализируем сбор статистики
        initGCMonitoring(statistics);

        //запускаем сам тест
        Benchmark benchmark = new Benchmark(2500, 15000);
        benchmark.runCalculations();

        //выводим результаты
        System.out.println(statistics.toString());
        System.out.println("ALL RUN TIME: " + (System.currentTimeMillis() - start));


    }

    /**
     * пишем статистику по сборкам
     *
     * @param statistics мапа для сохранения статистики
     */
    private static void initGCMonitoring(Map<String, Long> statistics) {

        List<GarbageCollectorMXBean> gcBeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        gcBeans.forEach(garbageCollectorMXBean -> {
            NotificationEmitter notificationEmitter = (NotificationEmitter) garbageCollectorMXBean;
            NotificationListener gcListener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    if (garbageCollectorMXBean.getName().equalsIgnoreCase("PS Scavenge")
                            || garbageCollectorMXBean.getName().equalsIgnoreCase("G1 Young Generation")) {
                        statistics.put("YOUNG GC COUNT", garbageCollectorMXBean.getCollectionCount());
                        statistics.put("YOUNG GC TIME", garbageCollectorMXBean.getCollectionTime());

                    } else if (garbageCollectorMXBean.getName().equalsIgnoreCase("PS MarkSweep")
                            || garbageCollectorMXBean.getName().equalsIgnoreCase("G1 Old Generation")) {
                        statistics.put("OLD GC COUNT", garbageCollectorMXBean.getCollectionCount());
                        statistics.put("OLD GC TIME", garbageCollectorMXBean.getCollectionTime());
                    }
                }
            };
            notificationEmitter.addNotificationListener(gcListener, null, null);
        });
    }
}
