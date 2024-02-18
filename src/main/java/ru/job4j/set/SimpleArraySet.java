package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean ret = !contains(value);
        if (ret) {
            set.add(value);
        }
        return ret;
    }

    @Override
    public boolean contains(T value) {
        boolean ret = false;
        for (int i = 0; i < set.size(); i++) {
            if ((value == null && value == set.get(i)) || value.equals(set.get(i))) {
                ret = true;
            }
        }
        return ret;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

}
