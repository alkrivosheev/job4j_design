package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        List<Integer> indexes = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path"))).useDelimiter(argsName.get("delimiter"))) {
            String[] head = {};
            if (scanner.hasNextLine()) {
                head = scanner.nextLine().split(argsName.get("delimiter"));
                indexes = getIndexList(head, argsName.get("filter").split(","));
            }
            if ("stdout".equals(argsName.get("out"))) {
                System.out.println(textPrepare(head, scanner, indexes, argsName.get("delimiter")));
            } else {
                try (BufferedOutputStream outputFile = new BufferedOutputStream(new FileOutputStream(argsName.get("out")))) {
                    outputFile.write(textPrepare(head, scanner, indexes, argsName.get("delimiter")).toString().getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> getIndexList(String[] titles, String[] filters) {
        List<Integer> indexes = new ArrayList<>();
        for (String filter : filters) {
            for (int i = 0; i < titles.length; i++) {
                if (filter.equals(titles[i])) {
                    indexes.add(i);
                }
            }
        }
        return indexes;
    }

    private static StringBuilder textPrepare(String[] head, Scanner scanner, List<Integer> indexes, String delimiter) {
        StringBuilder output = new StringBuilder();
        appendFields(output, head, indexes, delimiter);
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(delimiter);
            appendFields(output, line, indexes, delimiter);
        }
        return output;
    }
    private static void appendFields(StringBuilder output, String[] data, List<Integer> indexes, String delimiter) {
        for (int i = 0; i < indexes.size(); i++) {
            output.append(data[indexes.get(i)]);
            if (i < indexes.size() - 1) {
                output.append(delimiter);
            }
        }
        output.append(System.lineSeparator());
    }

    private static String getValFromArgument(String arg) {
        String[] params = arg.split("=", 2);
        return params[1].replaceFirst("-", "");
    }

    private static void validate(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Parameters are not specified. Usage: -path=filePath -delimiter=; or , or etc  -out=stdout or fileName -filter=age,last_name,name , etc");
        }
        if (!Files.exists(Path.of(getValFromArgument(args[0])))) {
            throw new IllegalArgumentException(String.format("The input file '%s' is not exists.", getValFromArgument(args[0])));
        }
    }
    public static void main(String[] args) throws Exception {
        validate(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
