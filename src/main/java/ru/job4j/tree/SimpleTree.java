package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> current = queue.poll();
            if (condition.test(current)) {
                return Optional.of(current);
            }
            queue.addAll(current.children);
        }

        return Optional.empty();
    }

    public boolean isBinary() {
        Predicate<Node<E>> moreThanTwoChildren = node -> node.children.size() > 2;
        return !findByPredicate(moreThanTwoChildren).isPresent();
    }

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Predicate<Node<E>> findParent = node -> node.value.equals(parent);
        Predicate<Node<E>> findChild = node -> node.value.equals(child);
        if (findByPredicate(findParent).isPresent() && !findByPredicate(findChild).isPresent()) {
            Node<E> parentNode = findByPredicate(findParent).get();
            parentNode.children.add(new Node<>(child));
            return true;
        }
        return false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.value.equals(value)) {
                result = Optional.of(element);
                break;
            }
            data.addAll(element.children);
        }
        return result;
    }
}