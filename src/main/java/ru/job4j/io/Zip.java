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

    private void importValues(String[] args) {
        ArgsName jvm = ArgsName.of(args);
        String[] keys = {"d", "e", "o"};
        for (String key : keys) {
            String val = jvm.get(key);
            this.values.put(key, val);
            this.validateParams(key, val);
        }
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
        zip.importValues(args);
        String d = zip.values.get("d");
        List<Path> paths = Search.search(java.nio.file.Path.of(d), path -> !path.toFile().getName().endsWith(zip.values.get("e")));
        zip.packFiles(paths, Path.of(zip.values.get("o")).toFile());
    }
}
