package ru.otus.homework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class NumberSequence<T> {
    private List<Thread> runningThreads = new ArrayList<>(2);
    private Collection<T> iterateCollection;
    private boolean sequenceStarted = false;


    public void goSequence(Collection<T> collection) {
        this.iterateCollection = collection;


        runningThreads.add(new Thread(() -> iterateSequence(true), "Thread 1"));
        runningThreads.add(new Thread(() -> iterateSequence(false), "Thread 2"));

        runningThreads.forEach(Thread::start);
    }

    private synchronized void iterateSequence(boolean master) {
        try {

            for (T number : iterateCollection) {
                while (checkForWait(master)) {
                    wait();
                }
                System.out.println(Thread.currentThread().getName() + " - " + number);
                sequenceStarted = master;
                Thread.sleep(100);
                this.notifyAll();

            }
        } catch (InterruptedException e) {
            notifyAll();
            Thread.currentThread().interrupt();
        }

    }

    private boolean checkForWait(boolean master) {
        return runningThreads.stream().filter(Predicate.not(Thread::isInterrupted)).count() > 1 && master == sequenceStarted;

    }
}
