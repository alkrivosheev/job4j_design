package ru.job4j.io.duplicates;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> dubFiles;

    public DuplicatesVisitor() {
        this.dubFiles = new HashMap<>();
    }

    public Map<FileProperty, List<Path>> getDubFiles() {
        return this.dubFiles;
    }
    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {

        FileProperty fp = new FileProperty(attributes.size(), file.toFile().getName());
        List<Path> paths = new ArrayList<>();
        paths.add(file.toAbsolutePath());
        if (dubFiles.putIfAbsent(fp, paths) != null) {
            paths = dubFiles.get(fp);
            paths.add(file.toAbsolutePath());
            dubFiles.put(fp, paths);
        }
        return super.visitFile(file, attributes);
    }
}