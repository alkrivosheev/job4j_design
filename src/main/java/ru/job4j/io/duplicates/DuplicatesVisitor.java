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
        dubFiles.computeIfAbsent(fp, p -> new ArrayList<>()).add(file.toAbsolutePath());
        return super.visitFile(file, attributes);
    }
}