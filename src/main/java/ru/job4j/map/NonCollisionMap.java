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

        int hash = Objects.hashCode(key);
        int index = indexFor(hash);
        if (index >= capacity - 1) {
            expand();
            index = indexFor(hash);
        }
//        System.out.println("hash = " + hash);
//        System.out.println("index = " + index);
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

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        capacity = capacity * 2;
        K key;
        for (int i = 0; i < count; i++) {
            if (table[i] != null) {
                key = table[i].key;
                int hash = Objects.hashCode(key);
                int index = indexFor(hash);
                newTable[index] = new MapEntry<>(key, table[i].value);
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V ret = null;
        int intKey = 0;
        intKey = (key == null) ? 0 : (int) key;

        if (intKey <= capacity - 1 && table[intKey] != null) {
            ret = table[intKey].value;
        }
        return ret;
    }

    @Override
    public boolean remove(K key) {
        boolean ret = false;
        if ((int) key <= count && table[(int) key] != null) {
            table[(int) key] = null;
            count--;
            modCount++;
            ret = true;
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

    public static void main(String[] args) {
        NonCollisionMap<Integer, String> map = new NonCollisionMap<>();
        System.out.println(map.put(1, "1"));
        System.out.println(map.put(2, "2"));
        System.out.println(map.put(3, "3"));
        System.out.println(map.put(4, "4"));
        System.out.println(map.put(null, "0000"));
        System.out.println(map.put(15, "15"));
        System.out.println(map.put(7, "7"));
        System.out.println(map.put(8, "8"));
        System.out.println(map.put(9, "9"));
//        System.out.println(map.remove(2));
//        map.remove(3);
//        map.put(null, "0000");
//        System.out.println(map.put(null, "0000"));
//        map.put(null, "1");
//        map.put(2, "2");
//        map.put(3, "3");
//        map.put(4, "4");
//        System.out.println(map.remove(4));
//        System.out.println(map.get(4));
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