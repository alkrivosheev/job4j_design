package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> current = queue.poll();
            if (condition.test(current)) {
                result =  Optional.of(current);
            }
            queue.addAll(current.children);
        }
        return result;
    }

    public boolean isBinary() {
        Predicate<Node<E>> moreThanTwoChildren = node -> node.children.size() > 2;
        return findByPredicate(moreThanTwoChildren).isEmpty();
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Predicate<Node<E>> findParent = node -> node.value.equals(parent);
        Predicate<Node<E>> findChild = node -> node.value.equals(child);
        if (findByPredicate(findParent).isPresent() && findByPredicate(findChild).isEmpty()) {
            Node<E> parentNode = findByPredicate(findParent).get();
            parentNode.children.add(new Node<>(child));
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> findElement = node -> node.value.equals(value);
        return findByPredicate(findElement);
    }
}