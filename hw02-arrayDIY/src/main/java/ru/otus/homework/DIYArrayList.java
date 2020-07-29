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
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * фактор роста коллекции
     */
    private final Double GROW_FACTOR = 1.6;
    /**
     * размер листа
     */
    private int size = 0;
    /**
     * счетчик изменений листа
     */
    private int modCounter = 0;
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
        ListIterator<T> iterator = this.getListIterator(0);
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return this.getListIterator(0);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean add(T t) {
        Objects.requireNonNull(t);
        calculateCapacity(1);
        elements[size++] = t;
        modCounter++;
        return true;
    }


    @Override
    public boolean remove(Object o) {
        ListIterator<T> iterator = this.getListIterator(0);
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), o)) {
                removeByShift(iterator.previousIndex());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addCollection(c, 0);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    private boolean addCollection(Collection<? extends T> c, int shift) {
        if (c.isEmpty()) {
            return false;
        }
        Object[] addedArray = c.toArray();
        Object[] oldElements = this.elements;
        calculateCapacity(addedArray.length);
        System.arraycopy(addedArray, 0, oldElements, this.size, c.size());
        modCounter++;
        size = size + addedArray.length;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        modCounter++;
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;

    }

    @Override
    public T get(int index) {
        indexValidityCheck(index);
        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        T oldValue = get(index);
        elements[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        indexValidityCheck(index);
        calculateCapacity(1);
        // "раздвигаем" массив для вставки
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
        modCounter++;

    }

    @Override
    public T remove(int index) {
        indexValidityCheck(index);
        removeByShift(index);
        return null;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        // тут идём с конца
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return this.getListIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return this.getListIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private ListIterator<T> getListIterator(int i) {
        return new ListIterator<T>() {
            // стартовая позиция итератора
            int currentPos = i - 1;
            // инициализируем счетчик изменения для итератора
            int iteratorModCounter = DIYArrayList.this.modCounter;
            // нужно для того чтобы не вызвать методы модификации на одной позиции больше одного раза
            int lastState = -1;

            @Override
            public boolean hasNext() {
                return currentPos + 1 < DIYArrayList.this.size();
            }

            @Override
            public T next() {
                checkForConcurrentModification();
                if (currentPos >= DIYArrayList.this.size()) {
                    throw new NoSuchElementException();
                }
                currentPos++;
                lastState = currentPos;
                return DIYArrayList.this.elements[currentPos];
            }

            @Override
            public boolean hasPrevious() {
                return currentPos != 0;
            }

            @Override
            public T previous() {
                checkForConcurrentModification();
                if (currentPos <= 0) {
                    throw new NoSuchElementException();
                }
                currentPos--;
                lastState = currentPos;
                return DIYArrayList.this.elements[currentPos];
            }

            @Override
            public int nextIndex() {
                return currentPos + 1;
            }

            @Override
            public int previousIndex() {
                return currentPos - 1;
            }

            @Override
            public void remove() {
                checkForIllegalOperation();
                checkForConcurrentModification();
                DIYArrayList.this.remove(currentPos);
                lastState = -1;
                updateModCount();
            }

            @Override
            public void set(T t) {
                checkForIllegalOperation();
                checkForConcurrentModification();
                DIYArrayList.this.set(currentPos, t);

            }

            @Override
            public void add(T t) {
                checkForIllegalOperation();
                checkForConcurrentModification();
                DIYArrayList.this.add(currentPos, t);
                lastState = -1;
                updateModCount();


            }

            /**
             * проверяем не менялся ли основной лист
             */
            private void checkForConcurrentModification() {
                if (iteratorModCounter != DIYArrayList.this.modCounter) {
                    throw new ConcurrentModificationException();
                }
            }

            /**
             * проверяем не было модификаций по текущей позиции
             */
            private void checkForIllegalOperation() {
                if (lastState < 0) {
                    throw new IllegalStateException();
                }
            }

            /**
             * обновление модификации, если они проходят из самого итератора
             */
            private void updateModCount() {
                iteratorModCounter = DIYArrayList.this.modCounter;
            }
        };
    }

    /**
     * Проверяем вместимость и если надо увеличиваем
     *
     * @param needAddCap
     */
    private void calculateCapacity(int needAddCap) {
        // удостоверимся что не переполним int
        int newNeedCapacity = Math.addExact(size(), needAddCap);
        if (elements.length < newNeedCapacity) {
            Double newCapacity = newNeedCapacity * GROW_FACTOR;
            elements = Arrays.copyOf(elements, newCapacity.intValue());
        }
    }

    /**
     * удаляем элемент "сдвигом"
     *
     * @param elementPos
     */
    private void removeByShift(int elementPos) {
        System.arraycopy(elements, elementPos + 1, elements, elementPos, size);
        modCounter++;
        //это нужно т.к. крайний элемент продублировался
        elements[size--] = null;

    }

    /**
     * проверка индекса
     *
     * @param index
     */
    private void indexValidityCheck(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
    }
}


