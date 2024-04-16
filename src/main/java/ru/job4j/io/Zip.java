package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private final Map<String, String> values = new HashMap<>();
    private String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            validateArgument(arg);
            String key = getKeyFromArgument(arg);
            String val = arg.split("=", 2)[1];
            validateParams(key, val);
            values.put(key, val);
        }
    }

    private String getKeyFromArgument(String arg) {
        String[] params = arg.split("=", 2);
        return params[0].replaceFirst("-", "");
    }

    private void validateParams(String key, String val) {
        if (!"d".equals(key) && !"e".equals(key) && !"o".equals(key)) {
            throw new IllegalArgumentException(String.format("Error: The key is not valid '%s' ", key));
        }
        if ("d".equals(key) && !Files.exists(Path.of(val))) {
            throw new IllegalArgumentException(String.format("Error: The folder not found '%s' ", val));
        }
        if ("d".equals(key) && !Files.isDirectory(Path.of(val))) {
            throw new IllegalArgumentException(String.format("Error: This is not Directory '%s' ", val));
        }
        if ("e".equals(key) && !val.startsWith(".")) {
            throw new IllegalArgumentException(String.format("Error: The file extension '%s' must start with '.'", val));
        }
        if ("o".equals(key) && !val.endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("Error: The file extension '%s' must end with '.zip'", val));
        }
    }
    private void validateArgument(String arg) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not start with a '-' character", arg));
        }
        if (!arg.contains("=")) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain an equal sign", arg));
        }
        String key = getKeyFromArgument(arg);
        if (key.isBlank()) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a key", arg));
        }
        String val = arg.split("=", 2)[1];
        if (val.isBlank()) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a value", arg));
        }
    }
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        Zip zip = new Zip();
        zip.parse(args);
        String d = zip.get("d");
        List<Path> paths = Search.search(java.nio.file.Path.of(d), path -> !path.toFile().getName().endsWith(zip.get("e")));
        zip.packFiles(paths, Path.of(zip.get("o")).toFile());
    }
}
