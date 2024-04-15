package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not start with a '-' character", arg));
            }
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain an equal sign", arg));
            }
            String[] params = arg.split("=", 2);
            String key = params[0].replaceFirst("-", "");
            if ("".equals(key)) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a key", arg));
            }
            String val = params[1];
            if ("".equals(val)) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a value", arg));
            }
            values.put(key, val);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
