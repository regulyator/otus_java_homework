package ru.otus.homework;

import java.util.Collection;

public class NumberSequence<T> {
    private Collection<T> iterateCollection;
    private T value;


    public void goSequence(Collection<T> collection) {
        this.iterateCollection = collection;

        Thread threadMaster = new Thread(this::masterProcess, "Thread 1");
        Thread threadSlave = new Thread(this::slaveProcess, "Thread 2");


        threadMaster.start();
        threadSlave.start();

        try {
            threadMaster.join();
            threadSlave.interrupt();
            threadSlave.join();
        } catch (InterruptedException e) {
            threadMaster.interrupt();
            threadSlave.interrupt();
        }
    }

    private synchronized void masterProcess() {
        try {
            for (T number : iterateCollection) {
                while (value != null) {
                    wait();
                }

                System.out.println(Thread.currentThread().getName() + " - " + number);
                value = number;
                notifyAll();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private synchronized void slaveProcess() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                while (value == null) {
                    wait();
                }
                System.out.println(Thread.currentThread().getName() + " - " + value);
                value = null;
                notifyAll();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
