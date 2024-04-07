package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        List<String> properties = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(this.path))) {
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                if (!line.startsWith("#") && !"".equals(line)) {
                    properties.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String line : properties) {
            String[] words = line.split("=", 2);
            if (!line.contains("=")) {
                throw new IllegalArgumentException(String.format("A = not found in line '%s'", line));
            }
            if (line.startsWith("=") && line.matches("^[^=]*=$")) {
                throw new IllegalArgumentException(String.format("A key and value not found in line '%s'", line));
            }
            if (line.startsWith("=")) {
                throw new IllegalArgumentException(String.format("A key not found in line '%s'", line));
            }
            if (line.matches("^[^=]*=$")) {
               throw new IllegalArgumentException(String.format("A value not found in line '%s'", line));
            }
        values.put(words[0], words[1]);
        }
    }

    public String value(String key) {
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}