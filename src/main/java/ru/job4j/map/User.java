package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public String toString() {
        return name + '\'' + children;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static String binary(int number) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            buffer.append(number % 2 == 0 ? 0 : 1);
            buffer.append((i + 1) % 8 == 0 ? " " : "");
            number /= 2;
        }
        return buffer.reverse().toString();
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>(16);
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Alex", 21, birthday);
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;

        User user2 = new User("Alex", 21, birthday);
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;

        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.printf("user1 - хэшкод: %s, хэш: %s, бакет: %s", hashCode1, hash1, bucket1);
        System.out.println();
        System.out.printf("user2 - хэшкод: %s, хэш: %s, бакет: %s", hashCode2, hash2, bucket2);
        System.out.println();
        System.out.println(map.keySet());
        System.out.println(binary(hashCode2));
        System.out.println(binary(hash2));
    }

}
