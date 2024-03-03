package ru.job4j.map;

import java.util.Iterator;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        return false;
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

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    public static void main(String[] args) {
        NonCollisionMap<Integer, String> map = new NonCollisionMap<>();
        System.out.println(map.hash(65535));
        System.out.println(map.indexFor(9));
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