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

    private Map<FileProperty, String> files;
    private Map<FileProperty, List<String>> dubFiles;

    public DuplicatesVisitor() {
        this.files = new HashMap<>();
        this.dubFiles = new HashMap<>();
    }

    public Map<FileProperty, List<String>> getDubFiles() {
        return this.dubFiles;
    }
    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {

        FileProperty fp = new FileProperty(attributes.size(), file.toFile().getName());
        if (attributes.isRegularFile() && files.putIfAbsent(fp, file.toAbsolutePath().toString()) != null) {
            if (dubFiles.get(fp) == null) {
                List<String> paths = new ArrayList<>();
                paths.add(files.get(fp));
                paths.add(file.toAbsolutePath().toString());
                dubFiles.put(fp, paths);
            } else {
                List<String> paths = dubFiles.get(fp);
                paths.add(file.toAbsolutePath().toString());
            }
        }
        return super.visitFile(file, attributes);
    }
}