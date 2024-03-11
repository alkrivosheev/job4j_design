package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;
    private int currentIndex = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean ret = false;
        int index = tableIndex(key);
        if (index >= capacity * LOAD_FACTOR) {
            expand();
            index = tableIndex(key);
        }
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            ret = true;
        }
        return ret;
    }

    private int hash(int hashCode) {
        int ret = 0;
        if (hashCode != 0) {
            ret = hashCode ^ (hashCode >>> 16);
        }
        return ret;
    }

    private int indexFor(int hash) {
        int ret = 0;
        if (hash != 0) {
            ret = capacity - 1 & hash;
        }
        return ret;
    }

    private int tableIndex(K key) {
        int hashCode = Objects.hashCode(key);
        int hash = hash(hashCode);
        return indexFor(hash);
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        capacity = capacity * 2;
        K key;
        for (int i = 0; i < count; i++) {
            if (table[i] != null) {
                key = table[i].key;
                int index = tableIndex(key);
                newTable[index] = new MapEntry<>(key, table[i].value);
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V ret = null;
        int index = tableIndex(key);
        if (index <= capacity && table[index] != null) {
            if (Objects.hashCode(table[index].key) == Objects.hashCode(key) && Objects.equals(table[index].key, key)) {
                ret = table[index].value;
            }
        }
        return ret;
    }

    @Override
    public boolean remove(K key) {
        boolean ret = false;
        int index = tableIndex(key);
        if (index <= capacity && table[index] != null) {
            if (Objects.hashCode(table[index].key) == Objects.hashCode(key) && Objects.equals(table[index].key, key)) {
                table[index] = null;
                count--;
                modCount++;
                ret = true;
            }
        }
        return ret;
    }

    @Override
    public Iterator<K> iterator() {
        currentIndex = 0;
        final int expectedModCount = modCount;
        return new Iterator<K>() {
            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (currentIndex < capacity && table[currentIndex] == null) {
                    currentIndex++;
                }
                return currentIndex < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[currentIndex++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}