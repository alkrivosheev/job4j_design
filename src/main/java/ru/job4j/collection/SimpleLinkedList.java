package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private int modCount;
    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node(value, null);
        if (size == 0) {
            head = newNode;
        } else {
            Node<E> node = head;
            for (int i = 1; i < size; i++) {
                node = node.next;
            }
            node.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = head;
        for (int i = 0; i < size - 1; i++) {
            if (i == index) {
                break;
            }
            node = node.next;
        }
        return node.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = head;
            final  int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                checkForModification();
                return current != null;
            }

            @Override
            public E next() {
                checkForModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E item = current.item;
                current = current.next;
                return item;
            }
            private void checkForModification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
