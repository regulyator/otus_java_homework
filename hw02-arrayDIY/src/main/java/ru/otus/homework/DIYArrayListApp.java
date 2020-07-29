package ru.otus.homework;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DIYArrayListApp {

    private static final int TEST_DATA_SIZE = 5000;

    public static void main(String[] args) {


        // наборы данных для проверки
        String[] randomStringData = new String[TEST_DATA_SIZE];
        List<String> randomStringList = new ArrayList<>(TEST_DATA_SIZE);

        // эталонная коллекция
        List<String> refList = new ArrayList<>();
        // тестовая коллекция
        List<String> diyList = new DIYArrayList<>();

        // заполняем тестовые данные
        for (int i = 0; i < TEST_DATA_SIZE; i++) {
            randomStringData[i] = RandomStringUtils.randomAlphabetic(5);
            randomStringList.add(RandomStringUtils.randomAlphabetic(5));
        }

        System.out.println("CHECK:  Collections.addAll(Collection<? super T> c, T... elements)");
        Collections.addAll(refList, randomStringData);
        Collections.addAll(diyList, randomStringData);
        compareLists(refList, diyList);
        System.out.println("=====================================================================");

        System.out.println("CHECK:  Collections.static <T> void copy(List<? super T> dest, List<? extends T> src)");
        Collections.copy(refList, randomStringList);
        Collections.copy(diyList, randomStringList);
        compareLists(refList, diyList);
        System.out.println("=====================================================================");

        System.out.println("CHECK:  Collections.static <T> void sort(List<T> list, Comparator<? super T> c)");
        Collections.sort(refList, String::compareTo);
        Collections.sort(diyList, String::compareTo);
        compareLists(refList, diyList);
        System.out.println("=====================================================================");
    }

    private static <T> void compareLists(List<? extends T> refList, List<? extends T> diyList) {
        if (refList.size() == refList.size()) {
            for (int i = 0; i < refList.size(); i++) {
                if (!Objects.equals(refList.get(i), diyList.get(i))) {
                    System.out.println("FAIL!");
                    return;
                }
            }
            System.out.println("SUCCESS!");
        } else {
            System.out.println("OH, DIFFERENT COLLECTIONS SIZE:(!");
        }

    }
}
