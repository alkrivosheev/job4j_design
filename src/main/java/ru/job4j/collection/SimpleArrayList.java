package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;


    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            containerIncrease();
        }
        container[size++] = value;
        modCount++;
    }
    private void containerIncrease() {
        int inc = (container.length == 0) ? 1 : container.length * 2;
        container = Arrays.copyOf(container, inc);
    }

    @Override
    public T set(int index, T newValue) {
        T oldVal = get(index);
        container[index] = newValue;
        return oldVal;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T oldVal = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return oldVal;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int point;
            final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size && size != 0;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No element");
                }
                return container[point++];
            }
        };
    }
}
