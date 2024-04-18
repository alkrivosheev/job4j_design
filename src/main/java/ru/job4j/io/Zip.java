package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private void validateParams(ArgsName jvm) {
        if (!Files.isDirectory(Path.of(jvm.get("d")))) {
            throw new IllegalArgumentException(String.format("Error: This is not Directory '%s' ", jvm.get("d")));
        }
        if (!jvm.get("e").startsWith(".")) {
            throw new IllegalArgumentException(String.format("Error: The file extension '%s' must start with '.'", jvm.get("e")));
        }
        if (!jvm.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("Error: The file extension '%s' must end with '.zip'", jvm.get("o")));
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
        ArgsName jvm = ArgsName.of(args);
        zip.validateParams(jvm);
        String d = jvm.get("d");
        List<Path> paths = Search.search(java.nio.file.Path.of(d), path -> !path.toFile().getName().endsWith(jvm.get("e")));
        zip.packFiles(paths, Path.of(jvm.get("o")).toFile());
    }
}
