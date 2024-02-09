package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int count;

    public T poll() {
        T ret;
        if (count == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        for (int i = 1; i < count; i++) {
            output.push(input.pop());
        }
        ret = input.pop();
        for (int i = 1; i < count; i++) {
            input.push(output.pop());
        }
        count--;
        return ret;
    }

    public void push(T value) {
        input.push(value);
        count++;
    }
}
