package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> res = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(this.file))) {
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                if (line.lastIndexOf(" 404 ") != -1) {
                    res.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);

    }
}
