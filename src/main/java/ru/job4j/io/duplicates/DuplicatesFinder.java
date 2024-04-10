package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor dubVis = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), dubVis);
        Map<FileProperty, List<String>> dublicates = dubVis.getDubFiles();
        for (FileProperty fp: dublicates.keySet()) {
            System.out.println(String.format("%s - %sMb", fp.getName(), fp.getSize() / 1024 / 1024));
            List paths = dublicates.get(fp);
            paths.forEach(System.out::println);
        }
    }
}
