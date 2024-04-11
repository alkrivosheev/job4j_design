package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (validate(args)) {
            Path start = Paths.get(args[0]);
            search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }
    }

    public static boolean validate(String[] args) {
        boolean res;
        if (args.length < 2) {
            throw new IllegalArgumentException("Parameters are not specified. Usage: ROOT_FOLDER file_extension");
        } else if (!Files.exists(Path.of(args[0]))) {
            throw new IllegalArgumentException("Use folder name for search. Usage: ' . ' or ' C:\\' ");
        } else if (!args[1].matches("\\.\\w+$")) {
            throw new IllegalArgumentException("Set file extension. Usage: '.exe' or '.js'");
        }else {
            res = true;
        }

        return res;
    }
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
