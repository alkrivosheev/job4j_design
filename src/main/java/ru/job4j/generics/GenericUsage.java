package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenericUsage {
    public static void main(String[] args) {
            List list = new ArrayList();
            list.add("first");
            list.add("second");
            list.add("third");
        list.add(new Person("name", 21, new Date(913716000000L)));
        System.out.println("Количество элементов в списке: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            String line = (String) list.get(i);
            System.out.println("Текущий элемент: " + line);
        }
        }
    }
