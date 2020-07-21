package ru.otus.homework;

import java.util.*;

/**
 * Коллекция для ДЗ
 * не поддерживает добавление null элементов
 *
 * @param <T>
 */
public final class DIYArrayList<T> implements List<T> {

    /**
     * capacity по умолчанию
     */
    private static final int DEFAULT_CAPACITY = 1;
    /**
     * фактор роста коллекции
     */
    private final int GROW_FACTOR = 2;
    /**
     * размер листа
     */
    private int size = 0;
    /**
     * тут собственно храним элементы
     */
    private T[] elements;

    public DIYArrayList() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return this.getIterator(0);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }


    @Override
    public boolean add(T t) {
        Objects.requireNonNull(t);
        calculateCapacity(1);
        elements[size] = t;
        size++;
        return true;
    }

    /**
     * Проверяем вместимость и если надо увеличиваем
     *
     * @param i
     */
    private void calculateCapacity(int i) {
        if (elements.length < elements.length + i) {
            int newCapacity = elements.length * GROW_FACTOR;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return getListIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private Iterator<T> getIterator(int i) {

        return new Iterator<>() {
            int currentPos = i;

            @Override
            public boolean hasNext() {
                return currentPos != elements.length;
            }

            @Override
            public T next() {
                if (currentPos >= elements.length) {
                    throw new NoSuchElementException();
                }
                currentPos++;
                return elements[currentPos];
            }
        };
    }

    private ListIterator<T> getListIterator(int i) {
        return new ListIterator<T>() {
            int currentPos = i;

            @Override
            public boolean hasNext() {
                int currentPos = i;
            }

            @Override
            public T next() {
                if (currentPos >= elements.length) {
                    throw new NoSuchElementException();
                }
                currentPos++;
                return elements[currentPos];
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public T previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(T t) {

            }

            @Override
            public void add(T t) {

            }
        };
    }
}


